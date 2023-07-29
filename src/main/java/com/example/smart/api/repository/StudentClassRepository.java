/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.GenericRepository;
import com.example.smart.models.StudentClass;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PC
 */
public interface StudentClassRepository extends GenericRepository<StudentClass, Integer> {
    Optional<List<StudentClass>> findAllByStudentId(Integer id);
    Optional<List<StudentClass>> findAllByClassId(Integer id);
    StudentClass findStudentClassByStudentId(Integer id);
    Optional<List<StudentClass>> findStudentClassesByStudentId(Integer id);
    StudentClass findStudentClassByClassIdAndStudentId(Integer classId,Integer studentId);
}
