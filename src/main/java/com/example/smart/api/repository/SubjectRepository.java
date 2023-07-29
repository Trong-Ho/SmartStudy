/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.Subject;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<List<Subject>> findAllBySemesterId(Integer semester);

    List<Subject> findSubjectsBySemesterIdIn(List<Integer> semesterId);

    List<Subject> findSubjectsBySemesterIdBetween(Integer fromSemester, Integer toSemester);

    Optional<Subject> findSubjectBySubjectCode(String subjectCode);

    List<Subject> findSubjectsBySubjectCode(String subjectCode);
}
