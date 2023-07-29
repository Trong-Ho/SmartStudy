/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IMark;
import com.example.smart.dto.ResponseModel;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.generic.GenericController;
import com.example.smart.models.Mark;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/api/mark")
public class MarkRestController extends GenericController<Mark>{
    @Autowired
    private IMark service;
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("mark") String jsonMark) {
        try {
            Mark mark = new ObjectMapper().readValue(jsonMark,Mark.class);
            service.save(mark);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Create success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Create fail"));
        }
    }
    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestParam("markList") String jsonMarkList) {
        try {
            List<Mark> markList = new ObjectMapper().readValue(jsonMarkList, new TypeReference<List<Mark>>() {});
            service.saveAll(markList);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Create success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Create fail"));
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("mark") String jsonMark){
        try {
            Mark mark = new ObjectMapper().readValue(jsonMark, Mark.class);
            service.save(mark);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Update success"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(),"Update fail"));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findMarkById(@PathVariable("id") Integer markId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), service.findOne(markId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(),"Don't find any records"));
        }
    }
    @GetMapping("/findMarkByStudentSubjectId/{id}")
    public Mark findMarkByStudentSubjectId(@PathVariable("id") Integer studentSubjectId){
        try {
            return service.findMarkByStudentSubjectId(studentSubjectId);
        }catch (Exception e){
            throw new ErrorHandler("Cannot found any record");
        }
    }
}
