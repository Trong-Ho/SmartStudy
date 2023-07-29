/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.GenericRepository;
import com.example.smart.models.Schedule;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PC
 */
public interface ScheduleRepository extends GenericRepository<Schedule, Integer> {

    Optional<Schedule> findScheduleAndByClassIdAndSemester(Integer classId, Integer semester);

    Optional<Schedule> findScheduleByClassId(Integer classId);

    Optional<List<Schedule>> findAllByClassId(Integer classId);

    List<Schedule> findSchedulesById(Integer scheduleId);
}
