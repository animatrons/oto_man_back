package com.oto.back.model.mapper;

import com.oto.back.model.Rider;
import com.oto.back.model.dto.RiderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RiderMapper extends IMapper<Rider, RiderDto> {
    RideMapper INSTANCE = Mappers.getMapper(RideMapper.class);
}
