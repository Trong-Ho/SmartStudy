/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.ClassSubject;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface ClassSubjectRepository extends JpaRepository<ClassSubject, Integer> {
     Optional<ClassSubject> findClassSubjectByClassIdAndSubjectId(int classId, int subjectId);
}
