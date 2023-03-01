package com.example.students.controller;

import com.example.students.model.Student;
import com.example.students.model.Test;
import com.example.students.service.StudentService;
import com.example.students.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestService testService;
    /*@GetMapping("/students/{studentId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "studentId") Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + studentId);
        }

        List<Comment> comments = commentRepository.findByTutorialId(studentId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }*/
    @GetMapping("/{studentId}/tests")
    public ResponseEntity<List<Double>> getAllTestsByStudentId(@PathVariable(value = "studentId") Long studentId){
        List<Test> tests = testService.findByStudentId(studentId);
        Double maximun = Math.round(testService.maximunGradeByStudentId(studentId) * 100.0)/100.0;
        Double minimun = Math.round(testService.minimunGradeByStudentId(studentId) * 100.0)/100.0;
        Double average = Math.round(testService.gradeAverageByStudentId(studentId) * 100.0)/100.0;
        Double tests_number = Double.valueOf(tests.size());
        List<Double> tests2 = new ArrayList<>();
        tests2.add(average);
        tests2.add(maximun);
        tests2.add(minimun);
        tests2.add(tests_number);
        StudentResponse studentResponse = new StudentResponse(average,maximun,minimun);
        return new ResponseEntity<>(tests2, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.update(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}