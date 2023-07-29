/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.service.StudentSubjectService;
import com.example.smart.dto.ResponseModel;
import com.example.smart.models.StudentSubject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/student-subject")
@Slf4j
public class StudentSubjectRestController {

    @Autowired
    private StudentSubjectService service;

    @PostMapping("/")
    public ResponseEntity<?> saveList(@RequestParam("student_subjectList") String studentSubjectList) throws JsonProcessingException {
        List<StudentSubject> list = new ObjectMapper().readValue(studentSubjectList, new TypeReference<List<StudentSubject>>() {
        });
        service.saveList(list);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("success", LocalTime.now().toString(), list));
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam("studentId") Integer studentId, @RequestParam("subjectId") Integer subjectId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), service.findOneStudentSubject(studentId, subjectId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Error", LocalDate.now().toString(), e.getMessage()));
        }
    }

    @GetMapping("/getByAttendanceId/{id}")
    public ResponseEntity<?> getByAttendanceId(@PathVariable("id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), service.findByAttendanceStudentSubjectId(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(), e.getMessage()));
        }
    }

        @GetMapping("/getByStudentId/{id}")
    public ResponseEntity<?> getByStudentId(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), service.findStudentSubjectByStudentId(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(), "Không tìm thấy dữ liệu"));
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), service.findStudentSubjectById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(), "Không tìm thấy dữ liệu"));
        }
    }

    @PostMapping("/findStudentSubjectBySubjectIdAndStudentId")
    public ResponseEntity<?> findStudentSubjectBySubjectIdAndStudentId(@RequestParam("subjectId") Integer subjectId,
                                                                       @RequestParam("studentId") Integer studentId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), service.findStudentSubjectBySubjectIdAndStudentId(subjectId, studentId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(), "Don't find any records"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("student_subject") String student_subject) {
        try {
            StudentSubject studentSubject = new ObjectMapper().readValue(student_subject, StudentSubject.class);
            service.update(studentSubject);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Cập nhật thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Cập nhật thất bại"));
        }
    }

    @PostMapping("/updateAll")
    public ResponseEntity<?> updateAll(@RequestParam("student_subject")String listStudent){
        try {
            List<StudentSubject> studentSubject = new ObjectMapper().readValue(listStudent, new TypeReference<List<StudentSubject>>() {});
            service.updateAll(studentSubject);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Cập nhật thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Cập nhật thất bại"));
        }
    }
}
