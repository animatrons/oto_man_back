package com.oto.back.model.mapper;

import java.util.List;

public interface IMapper<T, U> {

    T toEntity(U dto);
    U toDto(T entity);

    List<T> toEntities(List<U> dtos);
    List<U> toDtos(List<T> entities);
}
