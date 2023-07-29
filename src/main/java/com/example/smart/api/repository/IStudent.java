/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Student;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IStudent extends IGenericRepository<Student>{
    Student getByProfileId(Integer id);
    Student findStudentByStudentCard(String studentCard);
    Student findStudentCard(String studentCard);
    List<Student> findStudentIdByRangeStudentCard(List<String> listStudentCard);
}
