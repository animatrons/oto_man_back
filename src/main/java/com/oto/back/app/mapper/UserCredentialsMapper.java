package com.oto.back.app.mapper;

import com.oto.back.controller.security.domain.RegisterCredentials;
import com.oto.back.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCredentialsMapper extends IMapper<User, RegisterCredentials> {
    UserCredentialsMapper INSTANCE = Mappers.getMapper(UserCredentialsMapper.class);
}
