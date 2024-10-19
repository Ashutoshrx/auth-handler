package com.learn.jarvis.service.impl;

import com.learn.jarvis.service.JWTService;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTServiceImpl implements JWTService {

  /**
   * @param userName
   * @return token with 10 minutes(100*60*100) of expiration time
   */
  @Override
  public String generateToken(String userName) {
    String token = null;
    try {
      Date currentDate = Date.from(Instant.now());
      token = Jwts.builder().claims().add(new HashMap<>()).issuer(userName).
              issuedAt(currentDate).expiration(new Date(System.currentTimeMillis() + 100 * 60 * 100))
              .and().signWith(generatKey()).compact();
    } catch (Exception e) {
      System.out.println("Failed to generate Token for userName: " + userName);
    }

    return token;
  }

  /**
   * @return Generates the token with HMACSHA256 algorithm
   * @throws NoSuchAlgorithmException
   */
  private Key generatKey() throws NoSuchAlgorithmException {
    return KeyGenerator.getInstance("HmacSHA256").generateKey();
  }
}
