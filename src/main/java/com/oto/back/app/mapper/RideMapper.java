package com.oto.back.app.mapper;

import com.oto.back.app.AutoApp;
import com.oto.back.app.RiderApp;
import com.oto.back.model.Ride;
import com.oto.back.model.dto.RideDto;
import com.oto.back.model.dto.RiderDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class RideMapper implements IMapper<Ride, RideDto> {
    RiderMapper INSTANCE = Mappers.getMapper(RiderMapper.class);

    @Autowired
    protected RiderApp riderApp;
    @Autowired
    protected AutoApp autoApp;

    @Override
    @Mapping(source = "rider", target = "riderId", qualifiedByName = "dtoToId")
    public abstract Ride toEntity(RideDto dto);

    @Named("dtoToId")
    static String dtoToId(RiderDto riderDto) {
        return riderDto.getId();
    }

    @Override
    @Mapping(target = "rider", expression = "java(riderApp.getDtoForMapper(entity.getRiderId()))")
    @Mapping(target = "auto", expression = "java(autoApp.getDtoForMapper(entity.getAutoId()))")
    public abstract RideDto toDto(Ride entity);

}
