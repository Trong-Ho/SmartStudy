/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.ISubject;
import com.example.smart.dto.ResponseModel;
import com.example.smart.generic.GenericController;
import com.example.smart.models.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/subject")
public class SubjectRestController extends GenericController<Subject>{
    @Autowired
    public ISubject service;
    
    @PostMapping("/save")
    public ResponseEntity<?> post(@RequestParam("subject") String subjectJson){
        try {
            Subject subject = new ObjectMapper().readValue(subjectJson,Subject.class);
            service.save(subject);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(),"Save success"));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(),e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("subject") String subjectJson){
        try {
            Subject subject = new ObjectMapper().readValue(subjectJson,Subject.class);
            service.save(subject);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(),"Update success"));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(),e.getMessage()));
        }
    }
    
    @GetMapping("/findSubjectBySubjectCode/{code}")
    public ResponseEntity<?> findSubjectBySubjectCode(@PathVariable("code") String subjectCode){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(),service.findSubjectBySubjectCode(subjectCode)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(),e.getMessage()));
        }
    }

    @GetMapping("/getSubjectBySubjectId/{id}")
    public ResponseEntity<?> findSubjectBySubjectCode(@PathVariable("id") int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(),service.findOne(id)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(),e.getMessage()));
        }
    }

    @PostMapping("/findSubjectBySemesterId")
    public ResponseEntity<?> findSubjectsBySemesterIdBetween(@RequestParam("fromSemester")Integer fromSemester,@RequestParam("toSemester")Integer toSemester){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(),service.findSubjectsBySemesterIdBetween(fromSemester, toSemester)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(),"Don't find any records"));
        }
    }
}
