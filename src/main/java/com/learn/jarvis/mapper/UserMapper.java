package com.learn.jarvis.mapper;

import com.learn.jarvis.data.entity.User;
import com.learn.jarvis.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "password", expression = "java(decryptPassword(user.getPassword(), bCryptPasswordEncoder))")
  User mapToUser(UserDTO user, BCryptPasswordEncoder bCryptPasswordEncoder);

  default String decryptPassword(String password, BCryptPasswordEncoder bCryptPasswordEncoder) {
    return bCryptPasswordEncoder.encode(password);
  }

}
