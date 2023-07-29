/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.GenericRepository;
import com.example.smart.models.Classes;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author PC
 */
public interface ClassRepository extends GenericRepository<Classes, Integer> {
    @Query("SELECT c FROM Classes c WHERE c.classCode = :classCode")
    Optional<Classes> findClassesByClassCode(String classCode);

    @Query("SELECT c.classCode FROM Classes c WHERE c.classCode LIKE :classCode")
    List<String> searchClassesByClassCode(String classCode);

    List<Classes> findAllByTeacherId(Integer id);

    Optional<Classes> findClassesByTeacherIdAndId(Integer teacherId, Integer scheduleId);

    List<Classes> findAllByRoomId(Integer roomId);

}
