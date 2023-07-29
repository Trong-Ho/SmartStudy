/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.AttendanceRepository;
import com.example.smart.api.repository.IAttendance;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Attendance;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
@Slf4j
public class AttendanceService implements IAttendance{

    @Autowired
    private AttendanceRepository repo;

    @Override
    public void saveAllAttendance(List<Attendance> attendances) {
        try {
            repo.saveAll(attendances);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorHandler("Save fail");
        }    }

    @Override
    public List<Attendance> findAttendByDate(String date) {
        return repo.findAttendancesByDate(date).orElseThrow(() -> new ErrorHandler("Don't find any records"));
    }

    @Override
    public void save(Attendance attendance) {
        try {
            repo.save(attendance);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorHandler("Save fail");
        }    }

    @Override
    public void delete(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorHandler("Delete fail");
        }    }

    @Override
    public List<Attendance> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorHandler("Don't find any records");
        }    }

    @Override
    public Attendance findOne(int id) {
        try {
            return repo.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorHandler("Don't find attendance with id " + id);
        }    }

    
    

    
}
