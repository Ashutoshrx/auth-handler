package com.learn.jarvis.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @GetMapping("/greet")
  public String greetTheBoss() {
    return "The Admin is Ashutosh Satapathy, developed with Love <3";
  }
}
