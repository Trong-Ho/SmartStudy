/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IStudent;
import com.example.smart.dto.ResponseModel;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    
    @Autowired
    private IStudent iStudent;
    @PostMapping("/")
    public ResponseEntity<?> saveStudent(@ModelAttribute Student student){
        iStudent.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(),student));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(), iStudent.findOne(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(iStudent.findAll());
    }

    @GetMapping("/getByProfile/{id}")
    public ResponseEntity<?> findStudentByProfileId(@PathVariable("id")Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(iStudent.getByProfileId(id));
    }

    @GetMapping("/findStudentByStudentCard/{studentCard}")
    public ResponseEntity<?> findStudentByStudentCard(@PathVariable("studentCard") String studentCard){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iStudent.findStudentByStudentCard(studentCard));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/findStudentCard/{studentCard}")
    public ResponseEntity<?> findStudentCard(@PathVariable("studentCard") String studentCard){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iStudent.findStudentCard(studentCard));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //find student id by range student card
    @GetMapping("/findStudentIdByRangeStudentCard/{listStudentCard}")
    public ResponseEntity<?> findStudentIdByRangeStudentCard(@PathVariable("listStudentCard") String listStudentCard){
        try {
            List<String> stringList = new ObjectMapper().readValue(listStudentCard, List.class);
            List<Student> studentIdByRangeStudentCard = iStudent.findStudentIdByRangeStudentCard(stringList);
            return ResponseEntity.status(HttpStatus.OK).body(studentIdByRangeStudentCard);
        } catch (Exception e) {
            throw new ErrorHandler(e.getMessage());
        }
    }
}
