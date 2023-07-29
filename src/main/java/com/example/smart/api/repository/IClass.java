/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Classes;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IClass extends IGenericRepository<Classes> {

    Classes findByClassCode(String classCode);

    List<String> searchClassesByClassCode(String listStudentCard);

    List<Classes> findClassByTeacherId(Integer id);

    Classes findClassByTeacherIdAndScheduleId(Integer teacherId, Integer scheduleId);

    List<Classes> findClassByRoom(Integer roomId);

}
