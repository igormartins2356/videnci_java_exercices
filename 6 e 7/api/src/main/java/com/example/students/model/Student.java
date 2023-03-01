package com.example.students.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "student")
    private List<Test> tests;

    public Student() {}

    public Student(String name) {
        this.name = name;
    }
    public void setName( String name ){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
