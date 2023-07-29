/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Teacher;

/**
 *
 * @author PC
 */
public interface ITeacher extends IGenericRepository<Teacher>{
    Teacher findTeacherByProfileId(Integer id);
    Teacher findTeacherByCard(String card);
}
