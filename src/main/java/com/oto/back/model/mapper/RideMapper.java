package com.oto.back.model.mapper;

import com.oto.back.model.Ride;
import com.oto.back.model.dto.RideDto;
import com.oto.back.model.dto.RiderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RideMapper extends IMapper<Ride, RideDto> {
    RiderMapper INSTANCE = Mappers.getMapper(RiderMapper.class);

    @Override
    @Mapping(source = "rider", target = "riderId", qualifiedByName = "dtoToId")
    Ride toEntity(RideDto dto);

    @Named("dtoToId")
    public static String dtoToId(RiderDto riderDto) {
        return riderDto.getId();
    }


}
