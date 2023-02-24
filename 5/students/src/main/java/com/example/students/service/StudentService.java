package com.example.students.service;

import com.example.students.model.Student;
import com.example.students.model.Test;

import java.util.List;
import java.util.Optional;
public interface StudentService {
    Student save(Student student);
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student update(Student student);
    void deleteById(Long id);
}
