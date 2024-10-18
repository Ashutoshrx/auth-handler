package com.learn.jarvis.service;

import com.learn.jarvis.dto.UserDTO;

public interface UserService {
  UserDTO registerUser(UserDTO userRequest);
}
