package com.oto.back.dao.mapper;

import com.google.common.collect.Sets;
import com.oto.back.dao.util.ArraySqlValue;
import com.oto.back.model.AEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public abstract class AbstractRowMapper<T extends AEntity> {
    protected final Class<T> clazz;

    protected AbstractRowMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    private Field[] getFields() {
        Field[] fields = this.clazz.getDeclaredFields();
        List<Field> fieldsList = Arrays.stream(fields).sorted(Comparator.comparing(Field::getName)).toList();
        fields = fieldsList.toArray(new Field[0]);
        return fields;
    }

    private Map<String, Object> getNonNullFields(T entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> objectsMap = new HashMap<>();
        Field[] fields = getFields();

        for (Field field: fields) {
            var name = field.getName();
            var getter = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            Object obj = this.clazz.getMethod(getter).invoke(entity);
            Optional.ofNullable(obj).ifPresent(obj_ -> {
                objectsMap.put(name, obj_);
            });
        }
        if (entity.getId() != null) {
            objectsMap.put("id", entity.getId());
        }
        return objectsMap;
    }

    private Class<?> getGenericTypeOfField(String fName) throws NoSuchFieldException {
        Field[] fields = getFields();
        Class<?> aClass = Arrays.stream(fields).filter(f -> f.getName().equals(fName))
                .findFirst()
                .map(f -> {
                    ParameterizedType genericType = (ParameterizedType) f.getGenericType();
                    Class<?> typeArgument = (Class<?>) genericType.getActualTypeArguments()[0];
                    return typeArgument;
                }).orElseThrow(() -> new NoSuchFieldException("[[ OH NO ]]  FILED NOT FOUND!!!!"));
        return aClass;
    }

    public String getParametrizedInsertQuery(T entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, Object> propsMap = getNonNullFields(entity);
        var table = entity.getTableName();
        StringBuilder query = new StringBuilder();
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = propsMap.entrySet();
        for (Map.Entry<String, Object> keyValue: entrySet) {
            String key = keyValue.getKey();
            if (count == 0) query.append("INSERT INTO ").append(table).append(" (");
            query.append(key).append(count + 1 >= entrySet.size() ? ")" : ",");
            count++;
        }
        query.append(" VALUES (");
        for (int i = 0; i<count; i++) {
            query.append("?").append(i + 1 == count ? ") RETURNING id;" : " ,");
        }
        return query.toString();
    }

    public String getUpdateQuery(String id, T entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, Object> propsMap = getNonNullFields(entity);
        var table = entity.getTableName();
        StringBuilder queryLeft = new StringBuilder();
        StringBuilder queryRight = new StringBuilder();
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = propsMap.entrySet();
        for (Map.Entry<String, Object> keyValue: entrySet) {
            String key = keyValue.getKey();
            Object value = keyValue.getValue();
            if (value instanceof String) {
                value = "'" + value + "'";
            }
            if (value instanceof List<?> || value instanceof Set<?>) {
                StringBuilder arrStr = new StringBuilder();
                arrStr.append("ARRAY [");
                ((Collection<?>) value).forEach(v -> {
                    String item;
                    if (v instanceof String) {
                        item = "'" + v + "'";
                    } else {
                         item = v.toString();
                    }
                    arrStr.append(item).append(" ,");
                });
                arrStr.deleteCharAt(arrStr.length() - 1).append("]");
                value = arrStr.toString();
            }
            if (count == 0) {
                queryLeft.append("UPDATE ").append(table).append(" SET (");
                queryRight.append(" = (");
            }
            queryLeft.append(key).append(count + 1 >= entrySet.size() ? ") " : ",");
            queryRight.append(value).append(count + 1 >= entrySet.size() ? ") " : ",");
            count++;
        }
        queryRight.append(" WHERE id = ").append(id).append(" ;");
        queryLeft.append(queryRight);
        return queryLeft.toString();
    }

    public Object[] getNonNullMembersValues(T entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        List<Object> objects = new ArrayList<Object>();
        Map<String, Object> propsMap = getNonNullFields(entity);
        Set<Map.Entry<String, Object>> entrySet = propsMap.entrySet();
        for (Map.Entry<String, Object> keyValue: entrySet) {
            var v = keyValue.getValue();
            var k = keyValue.getKey();
            if (v instanceof List<?>) {
                Class<?> aClass = getGenericTypeOfField(k);
                v = ArraySqlValue.create(((List<?>) v).toArray(), aClass);
            }
            if (v instanceof Set<?>) {
                Class<?> aClass = getGenericTypeOfField(k);
                v = ArraySqlValue.create(((Set<?>) v).toArray(), aClass);
            }
            objects.add(v);
        }
        return objects.toArray();
    }

    public int[] getNonNullMembersTypes(T entity) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        List<Integer> types = new ArrayList<>();
        Map<String, Object> propsMap = getNonNullFields(entity);
        Set<Map.Entry<String, Object>> entrySet = propsMap.entrySet();
        for (Map.Entry<String, Object> keyValue: entrySet) {
            var v = keyValue.getValue();
            if (v instanceof Integer) {
                types.add(Types.INTEGER);
            } else
            if (v instanceof Long) {
                types.add(Types.BIGINT);
            } else
            if (v instanceof Float) {
                types.add(Types.FLOAT);
            } else
            if (v instanceof String) {
                types.add(Types.VARCHAR);
            } else
            if (v instanceof Boolean) {
                types.add(Types.BOOLEAN);
            } else
            if (v instanceof Date) {
                types.add(Types.TIMESTAMP_WITH_TIMEZONE);
            } else
            if (v instanceof List<?> || v instanceof Set<?>) {
                types.add(Types.ARRAY);
            } else
            if (v instanceof AEntity) {
                throw new UnsupportedOperationException("[[ OH NO ]]  AEntity type mapping is not implemented here. " +
                        "We agreed it should be done by the dto-entity mappers in the app layer, entities should not contain other entities.");
            } else {
                throw new NoSuchFieldException("[[ OH NO ]]  this type is not supported for this field");
            }
        }
        return types.stream().mapToInt(i->i).toArray();
    }


    protected T getMappedInstance(ResultSet rs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, NoSuchFieldException {
        var cTor = this.clazz.getConstructor();
        var instance = cTor.newInstance();

        var fields = getFields();
        for (Field field: fields) {
            var name = field.getName();
            var type = field.getType();
            var setter = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            var typeName = type.getCanonicalName();
            if (typeName.equals(String.class.getCanonicalName())) {
                instance.getClass().getMethod(setter, String.class).invoke(instance, rs.getString(name));
            } else
            if (typeName.equals(Integer.class.getCanonicalName())) {
                instance.getClass().getMethod(setter, Integer.class).invoke(instance, rs.getInt(name));
            } else
            if (typeName.equals(Boolean.class.getCanonicalName())) {
                instance.getClass().getMethod(setter, Boolean.class).invoke(instance, rs.getBoolean(name));
            } else
            if (typeName.equals(Date.class.getCanonicalName())) {
                instance.getClass().getMethod(setter, Date.class).invoke(instance, rs.getTimestamp(name));
            } else
            if (typeName.equals(Long.class.getCanonicalName())) {
                instance.getClass().getMethod(setter, Long.class).invoke(instance, rs.getLong(name));
            } else
            if (typeName.equals(Float.class.getCanonicalName())) {
                instance.getClass().getMethod(setter, Float.class).invoke(instance, rs.getFloat(name));
            } else
            if (typeName.equals(List.class.getCanonicalName())) {
                int baseType = rs.getArray(name).getBaseType();
                if (baseType == Types.VARCHAR || baseType == Types.NCHAR || baseType == Types.LONGNVARCHAR || baseType == Types.LONGVARCHAR || baseType == Types.NVARCHAR || baseType == Types.CHAR) {
                    String[] arr = (String[]) rs.getArray(name).getArray();
                    instance.getClass().getMethod(setter, List.class).invoke(instance, Arrays.asList(arr));
                } else if (baseType == Types.INTEGER || baseType == Types.BIGINT || baseType == Types.SMALLINT || baseType == Types.TINYINT) {
                    Integer[] arr = (Integer[]) rs.getArray(name).getArray();
                    instance.getClass().getMethod(setter, List.class).invoke(instance, Arrays.asList(arr));
                } else {
                    throw new NoSuchFieldException("[[ OH NO ]] The field cannot be mapped to a List because it's member's type is not supported");
                }
            } else
            if (typeName.equals(Set.class.getCanonicalName())) {
                int baseType = rs.getArray(name).getBaseType();
                if (baseType == Types.VARCHAR || baseType == Types.NCHAR || baseType == Types.LONGNVARCHAR || baseType == Types.LONGVARCHAR || baseType == Types.NVARCHAR || baseType == Types.CHAR) {
                    String[] arr = (String[]) rs.getArray(name).getArray();
                    instance.getClass().getMethod(setter, Set.class).invoke(instance, Sets.newHashSet(arr));
                } else if (baseType == Types.INTEGER || baseType == Types.BIGINT || baseType == Types.SMALLINT || baseType == Types.TINYINT) {
                    Integer[] arr = (Integer[]) rs.getArray(name).getArray();
                    instance.getClass().getMethod(setter, Set.class).invoke(instance, Sets.newHashSet(arr));
                } else {
                    throw new NoSuchFieldException("[[ OH NO ]] The field cannot be mapped to a Set because it's member's type is not supported");
                }
            } else {
                throw new NoSuchFieldException("[[ OH NO ]] this field cannot be mapped because it's type is not supported, filed is: " + name + " type: " + typeName);
            }
        }
        instance.setId(rs.getInt("id"));
        return instance;
    }
}
