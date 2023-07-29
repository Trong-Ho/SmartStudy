/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Mark;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IMark extends IGenericRepository<Mark>{
    void saveAll(List<Mark> markList);
    Mark findMarkByStudentSubjectId(int id);
}
