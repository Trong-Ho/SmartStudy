/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.ISchedule;
import com.example.smart.api.repository.ScheduleRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Schedule;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ScheduleService implements ISchedule {

    @Autowired
    ScheduleRepository repo;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        try {
            Schedule scheduleSave = repo.save(schedule);
            return scheduleSave;
        } catch (Exception e) {
            throw new ErrorHandler("Create fail");
        }
    }

    @Override
    public Schedule findScheduleByClassAndSemester(Integer classId, Integer semester) {
        return repo.findScheduleAndByClassIdAndSemester(classId, semester).orElse(null);
    }

    @Override
    public Schedule findScheduleByClass(Integer classId) {
        return repo.findScheduleByClassId(classId).orElseThrow(() -> new ErrorHandler("Cannot find schedule with class id = " + classId));
    }

    @Override
    public List<Schedule> findScheduleByClassID(Integer classId) {
        return repo.findAllByClassId(classId).orElseThrow(() -> new ErrorHandler("Cannot find schedule list with classId = " + classId));
    }

    @Override
    public List<Schedule> findScheduleById(Integer scheduleId) {
        try {
            List<Schedule> scheduleList = repo.findSchedulesById(scheduleId);
            return scheduleList;
        } catch (Exception e) {
            throw new RuntimeException("Error while getting schedule: " + e.getMessage());
        }
    }

    @Override
    public void save(Schedule schedule) {
        try {
            repo.save(schedule);
        } catch (Exception e) {
            throw new ErrorHandler("Create fail");
        }
    }

    @Override
    public void delete(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new ErrorHandler("Delete fail");
        }
    }

    @Override
    public List<Schedule> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new ErrorHandler("Get data fail");
        }
    }

    @Override
    public Schedule findOne(int id) {
        return repo.findById(id).orElseThrow(() -> new ErrorHandler("Don't find any data"));
    }
}
