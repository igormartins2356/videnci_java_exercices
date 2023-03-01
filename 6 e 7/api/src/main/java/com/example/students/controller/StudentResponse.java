package com.example.students.controller;

public class StudentResponse {
    private Double average;
    private Double maximun;
    private Double minimun;

    StudentResponse(Double average,Double maximun,Double minimun){
        this.average = average;
        this.maximun = maximun;
        this.minimun = minimun;
    }
}
