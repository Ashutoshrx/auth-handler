package com.learn.jarvis.service.impl;

import com.learn.jarvis.data.entity.Student;
import com.learn.jarvis.data.repository.StudentRepository;
import com.learn.jarvis.dto.StudentDTO;
import com.learn.jarvis.mapper.StudentMapper;
import com.learn.jarvis.service.StudentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private StudentMapper studentMapper;

  @Override
  public List<StudentDTO> findStudents() {
    List<Student> students = studentRepository.findAll();
    if (CollectionUtils.isNotEmpty(students)) {
      return students.stream().map(s -> studentMapper.mapToStudentResponse(s)).toList();
    }
    return null;
  }

  @Override
  public StudentDTO saveStudent(StudentDTO studentRequest) {
    Student student = studentMapper.mapToStudent(studentRequest);
    studentRepository.save(student);
    return studentRequest;
  }
}
