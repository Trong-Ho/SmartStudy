/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.StudentSubject;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {
    Optional<StudentSubject> findStudentSubjectByStudentIdAndSubjectId(Integer studentId, Integer subjectId);
    Optional<StudentSubject> findStudentSubjectsById(String id);
    List<StudentSubject> findStudentSubjectByStudentId(Integer studentId);
    StudentSubject findStudentSubjectById(Integer id);
    StudentSubject findStudentSubjectBySubjectIdAndStudentId(Integer subjectId, Integer studentId);
}
