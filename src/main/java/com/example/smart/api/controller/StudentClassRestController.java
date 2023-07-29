/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IStudentClass;
import com.example.smart.dto.ResponseModel;
import com.example.smart.models.StudentClass;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/student-classes")
public class StudentClassRestController {
    @Autowired
    private IStudentClass iStudentClass;
    
    @PostMapping("/")
    public ResponseEntity<?> saveStudentClasses(@RequestBody List<StudentClass> studentClasses){
        iStudentClass.saveAll(studentClasses);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), studentClasses));
    }
    
    @GetMapping("/getClassesByStudent/{id}")
    public ResponseEntity<?> findClassesByStudent(@PathVariable("id") Integer id){
        List<StudentClass> studentClasses = iStudentClass.findClassesByStudentId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), studentClasses));
    }
    
    @GetMapping("/getStudentsByClass/{id}")
    public ResponseEntity<?> findStudentsByClass(@PathVariable("id") Integer id){
        List<StudentClass> studentClasses = iStudentClass.findStudentByClassId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), studentClasses));
    }
    
    @GetMapping("/getClassByStudent/{id}")
    public ResponseEntity<?> findClassByStudent(@PathVariable("id") Integer id){
        StudentClass studentClass = iStudentClass.findClassByStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), studentClass));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            iStudentClass.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Delete success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), e.getMessage()));
        }
    }
}