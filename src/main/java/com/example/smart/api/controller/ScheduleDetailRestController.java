/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IScheduleDetail;
import com.example.smart.models.ScheduleDetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/schedule-details")
public class ScheduleDetailRestController {
    @Autowired
    private IScheduleDetail scheduleDetailService;
    
    @PostMapping("")
    public ResponseEntity<ScheduleDetail> create(@RequestBody ScheduleDetail scheduleDetail) {
        ScheduleDetail createdScheduleDetail = scheduleDetailService.save(scheduleDetail);
        return new ResponseEntity<>(createdScheduleDetail, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        scheduleDetailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<ScheduleDetail>> getByScheduleId(@RequestParam Integer scheduleId) {
        List<ScheduleDetail> scheduleDetails = scheduleDetailService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(scheduleDetails, HttpStatus.OK);
    }
}
