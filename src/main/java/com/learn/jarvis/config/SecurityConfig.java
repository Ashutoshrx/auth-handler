package com.learn.jarvis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
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
}
