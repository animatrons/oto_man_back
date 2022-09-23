package com.oto.back.app.mapper;

import com.oto.back.model.AEntity;
import com.oto.back.model.dto.ADto;

import java.util.List;

public interface IMapper<T extends AEntity, U extends ADto> {

    T toEntity(U dto);
    U toDto(T entity);
    List<T> toEntities(List<U> dtos);
    List<U> toDtos(List<T> entities);
}
