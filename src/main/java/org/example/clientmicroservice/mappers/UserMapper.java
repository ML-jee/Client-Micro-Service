package org.example.clientmicroservice.mappers;

import org.example.clientmicroservice.dto.UserDto;
import org.example.clientmicroservice.dto.SignUpDto;
import org.example.clientmicroservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toClientDto(User user);

    @Mapping(source = "genre", target = "genre")
    User signUpToClient(SignUpDto sIgnUpDto);
}
