package com.example.students.controller;

import com.example.students.model.Student;
import com.example.students.model.Test;
import com.example.students.service.StudentService;
import com.example.students.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private StudentService studentService;

    @GetMapping("tests")
    public ResponseEntity<List<Test>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(testService.findAll());
    }

    @GetMapping("tests/{id}")
    public ResponseEntity<Optional<Test>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(testService.findById(id));
    }

    @PostMapping("/students/{studentId}/tests")
    public ResponseEntity<Test> createTest(@PathVariable (value = "studentId") Long studentId,
                                       @RequestBody Test testRequest){
        Test test = studentService.findById(studentId).map(student -> {
            testRequest.setStudent(student);
            return testService.save(testRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Student with id = " + studentId));
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    /*@PutMapping
    public ResponseEntity<Test> update(@RequestBody Test test){
        return ResponseEntity.status(HttpStatus.OK).body(testService.update(test));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        testService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/

}