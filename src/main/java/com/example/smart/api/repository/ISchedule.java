/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Schedule;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ISchedule extends IGenericRepository<Schedule> {

    Schedule saveSchedule(Schedule schedule);

    Schedule findScheduleByClassAndSemester(Integer classId, Integer semester);

    Schedule findScheduleByClass(Integer classId);

    List<Schedule> findScheduleByClassID(Integer classId);

    List<Schedule> findScheduleById(Integer scheduleId);
}
