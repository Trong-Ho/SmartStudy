/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IScheduleDetail;
import com.example.smart.api.repository.ScheduleDetailRepository;
import com.example.smart.models.ScheduleDetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ScheduleDetailService implements IScheduleDetail{
    
    @Autowired
    private ScheduleDetailRepository repo;

    @Override
    public ScheduleDetail save(ScheduleDetail scheduleDetail) {
        return repo.save(scheduleDetail);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<ScheduleDetail> findByScheduleId(Integer scheduleId) {
        return repo.findByScheduleId(scheduleId);
    }
    
}
