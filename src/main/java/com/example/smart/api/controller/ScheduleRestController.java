/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.ISchedule;
import com.example.smart.dto.ResponseModel;
import com.example.smart.models.Schedule;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleRestController {

    @Autowired
    ISchedule service;
    
    @GetMapping("/findScheduleByClassAndSemester")
    public ResponseEntity<?> findScheduleByClassAndSemester(@RequestParam Integer classId, @RequestParam Integer semester) {
        try {
            Schedule schedule = service.findScheduleByClassAndSemester(classId, semester);
            if (schedule == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting schedule: " + e.getMessage());
        }
    }

    @GetMapping("/findScheduleByClass")
    public ResponseEntity<?> findScheduleByClass(@RequestParam Integer classId) {
        try {
            Schedule schedule = service.findScheduleByClass(classId);
            if (schedule == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting schedule: " + e.getMessage());
        }
    }

    @GetMapping("/findScheduleByClassID")
    public ResponseEntity<?> findScheduleByClassID(@RequestParam Integer classId) {
        try {
            List<Schedule> scheduleList = service.findScheduleByClassID(classId);
            if (scheduleList == null || scheduleList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting schedule: " + e.getMessage());
        }
    }

    @GetMapping("/findScheduleById/{scheduleId}")
    public ResponseEntity<?> findScheduleById(@PathVariable Integer scheduleId) {
        try {
            List<Schedule> scheduleList = service.findScheduleById(scheduleId);
            if (scheduleList == null || scheduleList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting schedule: " + e.getMessage());
        }
    }

    @PostMapping("/saveSchedule")
    public ResponseEntity<?> saveSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule savedSchedule = service.saveSchedule(schedule);
            return ResponseEntity.ok(savedSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving schedule: " + e.getMessage());
        }
    }
    
    @GetMapping("/findOne/{scheduleId}")
    public ResponseEntity<?> findOne(@PathVariable("scheduleId") Integer scheduleId) {
        try {
            Schedule schedule = service.findOne(scheduleId);
            return ResponseEntity.status(HttpStatus.OK).body(schedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Error", LocalDateTime.now().toString(), "Don't find any records"));
        }
    }
}
