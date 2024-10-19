package com.learn.jarvis.service.impl;

import com.learn.jarvis.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
  private static String SECRET_KEY;

  /**
   * This is the time when key is generate using Hmac algorithm.
   */
  public JWTServiceImpl() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      SecretKey secretKey = keyGen.generateKey();
      SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param userName
   * @return token with 10 minutes(100*60*100) of expiration time
   */
  @Override
  public String generateToken(String userName) {
    String token = null;
    try {
      Date currentDate = Date.from(Instant.now());
      token = Jwts.builder().claims().add(new HashMap<>()).subject(userName).
              issuedAt(currentDate).expiration(new Date(System.currentTimeMillis() + 100 * 60 * 100))
              .and().signWith(getKey()).compact();
    } catch (Exception e) {
      System.out.println("Failed to generate Token for userName: " + userName);
    }
    return token;
  }

  /**
   * @param token
   * @return Generate userName based on token using Claims
   */
  @Override
  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public boolean validateToken(String authToken, UserDetails userDetails) {
    final String userName = extractUserName(authToken);

    return userName.equals(userDetails.getUsername()) && !isTokenExpired(authToken);
  }

  private boolean isTokenExpired(String authToken) {
    return extractExpiration(authToken).before(new Date());
  }

  private Date extractExpiration(String authToken) {
    return extractClaim(authToken, Claims::getExpiration);
  }

  /**
   * @param token
   * @param claimResolver
   * @param <T>
   * @return Extract claim based on token
   */
  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  /**
   * @param token
   * @return Extracts all the claims
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
  }

  /**
   * @return Decodes the generated secret key
   */
  private SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
