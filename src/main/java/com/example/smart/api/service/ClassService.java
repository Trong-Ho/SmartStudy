/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.ClassRepository;
import com.example.smart.api.repository.IClass;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Classes;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ClassService implements IClass {
    private final ClassRepository repository;

    @Autowired
    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }
    
   @Override
    public Classes findByClassCode(String classCode) {
        return repository.findClassesByClassCode(classCode).orElseThrow(
                () -> new ErrorHandler("Cannot find class with class code := " + classCode));
    }

    @Override
    public List<String> searchClassesByClassCode(String classCode) {
        List<String> stringList = repository.searchClassesByClassCode(classCode);
        return stringList;
    }

    @Override
    public List<Classes> findClassByTeacherId(Integer id) {
        return repository.findAllByTeacherId(id);
    }

    @Override
    public Classes findClassByTeacherIdAndScheduleId(Integer teacherId, Integer scheduleId) {
        Optional<Classes> classesOption = repository.findClassesByTeacherIdAndId(teacherId, scheduleId);
        if (classesOption.isPresent()) {
            return classesOption.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Classes> findClassByRoom(Integer roomId) {
        List<Classes> list = repository.findAllByRoomId(roomId);
        if(list.isEmpty()){
            return null;
        }
        return repository.findAllByRoomId(roomId);
    }

    @Override
    public void save(Classes classes) {
        repository.save(classes);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Classes> findAll() {
        return repository.findAll();
    }

    @Override
    public Classes findOne(int id) {
        return repository.findById(id).orElseThrow(() -> new ErrorHandler("Cannot find class with id := " + id));
    }

    
    
}
