/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.AttendanceTrackingRepository;
import com.example.smart.api.repository.IAttendanceTracking;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.AttendanceTracking;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class AttendanceTrackingService implements IAttendanceTracking {

    @Autowired
    AttendanceTrackingRepository repo;

    @Override
    public void save(AttendanceTracking attendanceTracking) {
        repo.save(attendanceTracking);
    }

    @Override
    public void delete(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new ErrorHandler("Error while deleting record with id: " + id);
        }
    }

    @Override
    public List<AttendanceTracking> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new ErrorHandler("Error while getting all records");
        }
    }

    @Override
    public AttendanceTracking findOne(int id) {
        try {
            Optional<AttendanceTracking> result = repo.findById(id);
            return result.orElse(null);
        } catch (Exception e) {
            throw new ErrorHandler("Error while getting record with id: " + id);
        }
    }

    @Override
    public List<AttendanceTracking> findByTeacherId(Integer teacherId) {
        try {
            return repo.findAttendanceTrackingsByTeacherId(teacherId);
        } catch (Exception e) {
            throw new ErrorHandler("Error while getting records with teacherId: " + teacherId);
        }
    }
}
