package com.example.students.repository;

import com.example.students.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TestRepository extends JpaRepository<Test, Long> {

    @Query(value = "SELECT AVG(grade) FROM Tests INNER JOIN Students ON Tests.student_id = Students.id WHERE Students.id = :studentId", nativeQuery = true)
    Double gradeAverageByStudentId(@Param("studentId") Long studentId);

    @Query(value = "SELECT MIN(grade) FROM Tests INNER JOIN Students ON Tests.student_id = Students.id WHERE Students.id = :studentId", nativeQuery = true)
    Double minimunGradeByStudentId(@Param("studentId") Long studentId);
    @Query(value = "SELECT MAX(grade) FROM Tests INNER JOIN Students ON Tests.student_id = Students.id WHERE Students.id = :studentId", nativeQuery = true)
    Double maximunGradeByStudentId(@Param("studentId") Long studentId);

    @Query(value = "SELECT MAX(grade) FROM Tests", nativeQuery = true)
    Double maximunGrade();
    List<Test> findByStudentId(Long postId);
    void deleteByStudentId(Long studentId);

}