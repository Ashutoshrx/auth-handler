package com.learn.jarvis.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
  String generateToken(String userName);

  String extractUserName(String token);

  boolean validateToken(String authToken, UserDetails userDetails);
}
