package com.example.students.service;

import com.example.students.model.Test;
import com.example.students.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private TestRepository testRepository;

    @Override
    public Test save(Test student) {
        return testRepository.save(student);
    }

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    @Override
    public Test update(Test product) {
        return testRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public void deleteByStudentId(Long id) {
        testRepository.deleteByStudentId(id);
    }
    @Override
    public List<Test> findByStudentId(Long id) {
        return testRepository.findByStudentId(id);
    }
    @Override
    public Double gradeAverageByStudentId(Long studentId){
        return testRepository.gradeAverageByStudentId(studentId);
    }
    @Override
    public Double minimunGradeByStudentId(Long studentId){
        return testRepository.minimunGradeByStudentId(studentId);
    }

    @Override
    public Double maximunGradeByStudentId(Long studentId){
        return testRepository.maximunGradeByStudentId(studentId);
    }

    @Override
    public Double maximunGrade(){
        return testRepository.maximunGrade();
    }
}
