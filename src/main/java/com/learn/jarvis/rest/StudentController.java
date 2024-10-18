package com.learn.jarvis.rest;

import com.learn.jarvis.dto.StudentDTO;
import com.learn.jarvis.service.StudentService;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {
  @Autowired
  private StudentService studentService;


  @GetMapping("/students")
  public List<StudentDTO> getStudents() {
    return studentService.findStudents();
  }

  @PostMapping("/student")
  public StudentDTO getStudents(@RequestBody() StudentDTO student) {
    return studentService.saveStudent(student);
  }

  @GetMapping("/token")
  public CsrfToken getToken(HttpServletRequest httpServlet) {
    return (CsrfToken) httpServlet.getAttribute("_csrf");
  }

  @GetMapping("/servlet")
  public HttpServletMapping getCsrfToken(HttpServletRequest httpServlet) {
    System.out.println(httpServlet);
    return httpServlet.getHttpServletMapping();
  }

}
