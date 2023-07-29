/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IAttendance;
import com.example.smart.dto.ResponseModel;
import com.example.smart.generic.GenericController;
import com.example.smart.models.Attendance;
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
@RequestMapping("/api/attendance")
public class AttendanceRestController extends GenericController<Attendance>{

    @Autowired
    private IAttendance attendanceService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("attendance") String attendanceJson) {
        try {
            Attendance attendance = new ObjectMapper().readValue(attendanceJson, Attendance.class);
            attendanceService.save(attendance);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Save success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Save fail"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> edit(@RequestParam("attendance") String attendanceJson) {
        try {
            Attendance attendance = new ObjectMapper().readValue(attendanceJson, Attendance.class);
            attendanceService.save(attendance);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Update success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Update fail"));
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestParam("list_attendance") String listAttendance) {
        try {
            List<Attendance> listAttend = new ObjectMapper().readValue(listAttendance, new TypeReference<List<Attendance>>() {
            });
            attendanceService.saveAllAttendance(listAttend);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Update success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Update fail"));
        }
    }

    @PostMapping("/findAttendanceByDate")
    public ResponseEntity<?> findAttendanceByDate(@RequestParam("date") String date) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), attendanceService.findAttendByDate(date)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDate.now().toString(), "Don't find any records"));
        }
    }
}
