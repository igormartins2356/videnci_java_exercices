package com.example.students.service;

import com.example.students.model.Test;

import java.util.List;
import java.util.Optional;
public interface TestService {
    Test save(Test test);
    List<Test> findAll();
    Optional<Test> findById(Long id);
    Test update(Test student);
    void deleteById(Long id);
    void deleteByStudentId(Long id);
    List<Test> findByStudentId(Long id);
    Double gradeAverageByStudentId(Long studentId);
    Double minimunGradeByStudentId(Long studentId);
    Double maximunGradeByStudentId(Long studentId);
    Double maximunGrade();
}
