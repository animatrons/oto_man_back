package com.oto.back.model.mapper;

import com.oto.back.model.Ride;
import com.oto.back.model.dto.RideDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RideMapper extends IMapper<Ride, RideDto> {
    RiderMapper INSTANCE = Mappers.getMapper(RiderMapper.class);
}
