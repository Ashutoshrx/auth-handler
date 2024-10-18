package com.learn.jarvis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    //configuring my own security by taking userName and passwords from application.properties.
    return httpSecurity.csrf(AbstractHttpConfigurer::disable). //disables the default filter chain
            authorizeHttpRequests(requests -> requests.anyRequest().authenticated()) // authenticates all requests
            .httpBasic(Customizer.withDefaults())// read usernames and passwords from .properties file
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //An application that doesn't retain information about a user's previous interactions. This is more scalable
            .build();
  }

  /**
   * 1. daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());--->
   * We are taking control from springBoot to create our own AuthenticationProvider where we are not encoding the
   * password.
   * This is the not the best practice but one just for learning purpose.
   * 2. new BCryptPasswordEncoder(10)--> Since we are encrypting the password with the same encoder while registering
   * hence, we need to use the same encoder for authenticating as well.
   */

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }
}
