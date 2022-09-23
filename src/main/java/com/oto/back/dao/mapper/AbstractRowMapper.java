package com.oto.back.dao.mapper;

import com.oto.back.model.AEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

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

    public String getParametrizedInsertQuery(T entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, Object> propsMap = getNonNullFields(entity);
        var table = entity.getTableName();
        String query = "";
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = propsMap.entrySet();
        for (Map.Entry<String, Object> keyValue: entrySet) {
            String key = keyValue.getKey();
            if (count == 0) query += "INSERT INTO " + table + " (";
            query += key + ( count + 1 >= entrySet.size() ? ")" : ",");
            count++;
        }
        query += " VALUES (";
        for (int i = 0; i<count; i++) {
            query += "?" + (i + 1 == count ? ");" : " ,");
        }
        return query;
    }

    public Object[] getNonNullValueProperties(T entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Object> objects = new ArrayList<Object>();
        Map<String, Object> propsMap = getNonNullFields(entity);
        Set<Map.Entry<String, Object>> entrySet = propsMap.entrySet();
        for (Map.Entry<String, Object> keyValue: entrySet) {
            var v = keyValue.getValue();
            objects.add(v);
        }
        return objects.toArray();
    }

    public int[] getTypes(T entity) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
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
                types.add(Types.DATE);
            } else
            if (v instanceof AEntity) {
                throw new UnsupportedOperationException("[[ OH NO ]]  AEntity type mapping is not implemented here. " +
                        "We agreed it should be done by the dto to entity mappers in the app layer, entities should not contain other entities.");
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
            var instanceOfFieldType = type.getConstructor().newInstance();
            if (instanceOfFieldType instanceof String) {
                instance.getClass().getMethod(setter, String.class).invoke(instance, rs.getString(name));
            } else
            if (instanceOfFieldType instanceof Integer) {
                instance.getClass().getMethod(setter, Integer.class).invoke(instance, rs.getInt(name));
            } else
            if (instanceOfFieldType instanceof Boolean) {
                instance.getClass().getMethod(setter, Boolean.class).invoke(instance, rs.getBoolean(name));
            } else
            if (instanceOfFieldType instanceof Date) {
                instance.getClass().getMethod(setter, Date.class).invoke(instance, rs.getDate(name));
            } else
            if (instanceOfFieldType instanceof Long) {
                instance.getClass().getMethod(setter, Long.class).invoke(instance, rs.getLong(name));
            } else
            if (instanceOfFieldType instanceof Float) {
                instance.getClass().getMethod(setter, Float.class).invoke(instance, rs.getFloat(name));
            } else {
                throw new NoSuchFieldException("[[ OH NO ]] this field cannot be mapped because it's type is not supported, filed is: " + name + " type: " + instanceOfFieldType);
            }
        }
        instance.setId(rs.getInt("id"));
        return instance;
    }
}
