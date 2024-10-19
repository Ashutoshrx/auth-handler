package com.learn.jarvis.config;

import com.learn.jarvis.service.JWTService;
import com.learn.jarvis.service.impl.UserPrincipalService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Component
public class JwtCustomFilter extends OncePerRequestFilter {
  @Autowired
  private ApplicationContext applicationContext;
  @Autowired
  private JWTService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
      String authToken = authHeader.substring(7);
      if (StringUtils.isNotEmpty(authToken)) {
        String userName = jwtService.extractUserName(authToken);
        if (StringUtils.isNotEmpty(userName) && ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
          UserDetails userDetails = applicationContext.getBean(UserPrincipalService.class).loadUserByUsername(userName);
          if (jwtService.validateToken(authToken, userDetails)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
          }
        }
      }
    }
    filterChain.doFilter(request, response);
  }
}
