package com.learn.jarvis.rest;

import com.learn.jarvis.dto.StudentDTO;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {

  List<StudentDTO> students = new ArrayList<>(List.of(new StudentDTO(1, "Ashu", "Sata", 89),
          new StudentDTO(2, "Ronnie", "pathy", 45),
          new StudentDTO(3, "Ashutosh", "Satapathy", 29),
          new StudentDTO(4, "Ronnie", "Ashutosh", 12)));

  @GetMapping("/students")
  public List<StudentDTO> getStudents() {
    return students;
  }

  @PostMapping("/student")
  public StudentDTO getStudents(@RequestBody() StudentDTO student) {
    students.add(student);
    return student;
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
