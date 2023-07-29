/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IAttendanceTracking;
import com.example.smart.dto.ResponseModel;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.AttendanceTracking;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
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
@Slf4j
@RequestMapping("/api/attendance_tracking")
public class AttendanceTrackingRestController {
    @Autowired
    IAttendanceTracking service;
    
    @PostMapping("/saveTracking")
    public ResponseEntity<?> save(@RequestParam("attendance_tracking") String attendance) {
        try {
            AttendanceTracking attendanceTracking = new ObjectMapper().readValue(attendance, AttendanceTracking.class);
            service.save(attendanceTracking);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalDate.now().toString(), "Save success"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Error", LocalDate.now().toString(), "Post error"));
        }
    }
    
    @GetMapping("/findByTeacherId/{id}")
    public ResponseEntity<?> findByTeacherId(@PathVariable("id") Integer teacherId) {
        try {
        List<AttendanceTracking> trackingList = service.findByTeacherId(teacherId);
        return ResponseEntity.ok(trackingList);
    } catch (Exception e) {
        throw new ErrorHandler("Error while getting attendance tracking: " + e.getMessage());
    }
    }
}
