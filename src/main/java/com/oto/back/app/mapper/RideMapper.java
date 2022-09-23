package com.oto.back.app.mapper;

import com.oto.back.app.RiderApp;
import com.oto.back.app.UserApp;
import com.oto.back.model.Ride;
import com.oto.back.model.dto.ADto;
import com.oto.back.model.dto.RideDto;
import com.oto.back.model.dto.RiderDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RideMapper extends IMapper<Ride, RideDto> {
    RiderMapper INSTANCE = Mappers.getMapper(RiderMapper.class);

    @Override
    @Mapping(source = "rider", target = "riderId", qualifiedByName = "dtoToId")
    Ride toEntity(RideDto dto);

    @Named("dtoToId")
    static String dtoToId(RiderDto riderDto) {
        return riderDto.getId();
    }

    @Override
    @Mapping(source = "riderId", target = "rider", qualifiedByName = "idToDto")
    RideDto toDto(Ride entity);

    @Named("idToDto")
    static RiderDto idToDto(String riderId, @Context RiderApp riderApp) {
        return riderApp.get(riderId);
    }
}
