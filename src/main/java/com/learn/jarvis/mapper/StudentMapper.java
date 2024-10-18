package com.learn.jarvis.mapper;

import com.learn.jarvis.data.entity.Student;
import com.learn.jarvis.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
  StudentDTO mapToStudentResponse(Student student);
  Student mapToStudent(StudentDTO student);
}
