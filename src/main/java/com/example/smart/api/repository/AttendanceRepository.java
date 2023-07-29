/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.Attendance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
   Optional<List<Attendance>> findAttendancesByDate(String date);
}
