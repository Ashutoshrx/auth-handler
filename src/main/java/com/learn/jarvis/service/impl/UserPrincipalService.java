package com.learn.jarvis.service.impl;

import com.learn.jarvis.data.entity.User;
import com.learn.jarvis.data.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.learn.jarvis.constants.UserConstants.NO_USER_FOUND;

@Service
public class UserPrincipalService implements UserDetailsService {
  @Autowired
  private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByUserName(username);
    if (ObjectUtils.isEmpty(user)) {
      throw new UsernameNotFoundException(NO_USER_FOUND + username);
    }
    return user;
  }
}
