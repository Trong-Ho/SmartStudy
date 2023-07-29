/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.ScheduleDetail;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IScheduleDetail {
    ScheduleDetail save(ScheduleDetail scheduleDetail);
    void deleteById(Integer id);
    List<ScheduleDetail> findByScheduleId(Integer scheduleId);
}
