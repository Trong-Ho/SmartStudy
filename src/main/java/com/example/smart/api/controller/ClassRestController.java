/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IClass;
import com.example.smart.dto.ResponseModel;
import com.example.smart.generic.GenericController;
import com.example.smart.models.Classes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
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
@RequestMapping("/api/classes")
public class ClassRestController extends GenericController<Classes> {

    @Autowired
    private IClass classService;
    
    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<?> post(@RequestParam("newClass") String newclass) {
        try {
            Classes classes = new ObjectMapper().readValue(newclass, new TypeReference<Classes>() {
            });
            classService.save(classes);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(), newclass));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalTime.now().toString(), e.getMessage()));
        }
    }

    @PostMapping("/findClassCode")
    public ResponseEntity<?> findClassCode(@RequestParam("classCode") String classCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(), classService.findByClassCode(classCode)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalTime.now().toString(), null));
        }
    }

    @GetMapping("/searchClasssesByClassCode/{classCode}")
    public List<String> searchClassesByClassCode(@PathVariable("classCode") String classCode) {
        classCode = "%" + classCode + "%";
        return classService.searchClassesByClassCode(classCode);
    }

    @GetMapping("/getClass/{id}")
    public ResponseEntity<?> getClass(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(), classService.findOne(id)));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    @GetMapping("/findClassByTeacher/{id}")
    public ResponseEntity<?> findClassByTeacher(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(), classService.findClassByTeacherId(id)));
    }

    @PostMapping("/findClassByTeacherAndSchedule")
    public ResponseEntity<?> findClassByTeacherAndSchedule(@RequestParam("teacherId") Integer teacherId, @RequestParam("scheduleId") Integer scheduleId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), classService.findClassByTeacherIdAndScheduleId(teacherId, scheduleId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(), e.getMessage()));
        }
    }

    @PutMapping("/updateClass")
    public ResponseEntity<?> updateClass(@RequestParam("class") String classes) throws JsonProcessingException {
        Classes classe = new ObjectMapper().readValue(classes, Classes.class);
        classService.save(classe);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    

    @GetMapping("/findClassByRoom/{roomId}")
    public ResponseEntity<?> findClassByRoom(@PathVariable("roomId") Integer roomId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), classService.findClassByRoom(roomId)));
    }



}
   