package com.oto.back.app.mapper;

import com.oto.back.model.User;
import com.oto.back.model.dto.UserOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserOutputMapper extends IMapper<User, UserOutputDto> {

    UserOutputMapper INSTANCE = Mappers.getMapper(UserOutputMapper.class);
}
