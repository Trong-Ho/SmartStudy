/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IStudentClass;
import com.example.smart.api.repository.StudentClassRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.StudentClass;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
@Slf4j
public class StudentClassService implements IStudentClass {

    @Autowired
    private StudentClassRepository repository;
    
     @Override
    public void saveAll(List<StudentClass> list) {
        repository.saveAll(list);
    }


    @Override
    public List<StudentClass> findClassIdByStudentId(Integer id) {
        return repository.findAllByStudentId(id).orElseThrow(() -> new ErrorHandler("Cannot find class id with student id = " + id));
    }

    @Override
    public List<StudentClass> findStudentByClassId(Integer id) {
        return repository.findAllByClassId(id).orElseThrow(() -> new ErrorHandler("Cannot find student with id = " + id));
    }

    @Override
    public StudentClass findClassByStudent(Integer id) {
        try {
            StudentClass studentClass = new StudentClass();
            studentClass = repository.findStudentClassByStudentId(id);
            if (studentClass != null) {
                return studentClass;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorHandler("Don't find any records");
        }
    }

    @Override
    public List<StudentClass> findStudentClassesByStudentId(int id) {
        try {
            Optional<List<StudentClass>> studentClassesByStudentId = repository.findStudentClassesByStudentId(id);
            if (studentClassesByStudentId.isPresent()) return studentClassesByStudentId.get();
            else return null;
        }catch (Exception e){
            throw new ErrorHandler("Can not found any record");
        }
    }
    
    @Override
    public List<StudentClass> findClassesByStudentId(Integer id) {
        return repository.findStudentClassesByStudentId(id).orElseThrow(()->new ErrorHandler("Don't find any records"));
    }

    @Override
    public StudentClass findStudentClassByClassIdAndStudentId(Integer classId, Integer studentId) {
        return repository.findStudentClassByClassIdAndStudentId(classId, studentId);
    }

    @Override
    public void save(StudentClass studentClass) {
        repository.save(studentClass);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<StudentClass> findAll() {
        return repository.findAll();
    }

    @Override
    public StudentClass findOne(int id) {
        return repository.findById(id).orElseThrow(() -> new ErrorHandler("Cannot find id := " + id));
    }
    
}
