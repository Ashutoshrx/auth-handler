package com.learn.jarvis.service;

import com.learn.jarvis.dto.StudentDTO;

import java.util.List;

public interface StudentService {
  List<StudentDTO> findStudents();

  StudentDTO saveStudent(StudentDTO student);
}
