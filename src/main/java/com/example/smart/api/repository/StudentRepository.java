/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.GenericRepository;
import com.example.smart.models.Student;
import java.util.Optional;

/**
 *
 * @author PC
 */
public interface StudentRepository extends GenericRepository<Student, Integer> {
    Optional<Student> findStudentByProfileId(Integer integer);
    Optional<Student> findStudentByStudentCard(String card);
}
