/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.ITeacher;
import com.example.smart.dto.ResponseModel;
import com.example.smart.models.Teacher;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/teachers")
public class TeacherRestController {

    @Autowired
    private ITeacher iTeacher;

    @PostMapping("/")
    public ResponseEntity<?> saveTeacher(@ModelAttribute Teacher teacher) {
        iTeacher.save(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("success", LocalTime.now().toString(), teacher));
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllTeacher() {
        return ResponseEntity.status(HttpStatus.OK).body(iTeacher.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findTeacherById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("success", LocalTime.now().toString(), iTeacher.findOne(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("error", LocalTime.now().toString(), e.getMessage()));
        }
    }

    @GetMapping("/getByProfile/{id}")
    public ResponseEntity<?> findTeacherByProfileId(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTeacher.findTeacherByProfileId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalTime.now().toString(), e.getMessage()));
        }
    }

    @GetMapping("/getByCard/{card}")
    public ResponseEntity<?> findTeacherByCard(@PathVariable("card") String card) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTeacher.findTeacherByCard(card));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("error", LocalTime.now().toString(), e.getMessage()));
        }
    }
}
