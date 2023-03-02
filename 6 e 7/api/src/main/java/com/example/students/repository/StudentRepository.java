package com.example.students.repository;

import com.example.students.model.Student;
import com.example.students.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {}
