package com.example.students.controller;

import com.example.students.model.Student;
import com.example.students.model.Test;
import com.example.students.service.StudentService;
import com.example.students.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Test>> getAllTestsByStudentId(@PathVariable(value = "studentId") Long studentId){
        List<Test> tests = testService.findByStudentId(studentId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
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