package com.learn.jarvis.service.impl;

import com.learn.jarvis.data.entity.User;
import com.learn.jarvis.data.repository.UsersRepository;
import com.learn.jarvis.dto.UserDTO;
import com.learn.jarvis.mapper.UserMapper;
import com.learn.jarvis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  /**
   * 10 represents this encoder will hash the plainText to 2**10
   */
  private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
  @Autowired
  private UsersRepository usersRepository;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public UserDTO registerUser(UserDTO userRequest) {
    User user = userMapper.mapToUser(userRequest, bCryptPasswordEncoder);
    usersRepository.save(user);
    System.out.println("Successfully user " + userRequest.getUserName() + " has been registered to the system.");
    return userRequest;
  }

  @Override
  public String verifyAndLogInUser(UserDTO userRequest) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUserName(),
            userRequest.getPassword()));
    return authentication.isAuthenticated() ? "Success" : "Failure";
  }
}
