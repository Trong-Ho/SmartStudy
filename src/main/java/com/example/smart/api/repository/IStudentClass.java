/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.StudentClass;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IStudentClass extends IGenericRepository<StudentClass>{
    void saveAll(List<StudentClass> list);

    List<StudentClass> findClassIdByStudentId(Integer id);

    List<StudentClass> findStudentByClassId(Integer id);

    StudentClass findClassByStudent(Integer id);
    List<StudentClass> findStudentClassesByStudentId(int id);

    List<StudentClass> findClassesByStudentId(Integer id);
    StudentClass findStudentClassByClassIdAndStudentId(Integer classId,Integer studentId);

}
