package com.learn.jarvis.rest;

import com.learn.jarvis.dto.UserDTO;
import com.learn.jarvis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/v1/register")
  public UserDTO registerUser(@RequestBody UserDTO userRequest) {
    return userService.registerUser(userRequest);
  }
}
