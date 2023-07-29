/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Attendance;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IAttendance extends IGenericRepository<Attendance>{
    void saveAllAttendance(List<Attendance> attendances);

    List<Attendance> findAttendByDate(String date);

}
