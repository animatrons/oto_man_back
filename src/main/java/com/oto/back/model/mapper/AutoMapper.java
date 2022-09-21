package com.oto.back.model.mapper;

import com.oto.back.model.Auto;
import com.oto.back.model.dto.AutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutoMapper extends IMapper<Auto, AutoDto> {
    AutoMapper INSTANCE = Mappers.getMapper(AutoMapper.class);
}
