package com.learn.jarvis.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "students")
public class Student implements Serializable {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private Integer marks;
}
