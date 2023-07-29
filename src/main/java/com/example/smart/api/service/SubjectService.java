/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.ISubject;
import com.example.smart.api.repository.SubjectRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Subject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class SubjectService implements ISubject{
    @Autowired
    private SubjectRepository sub;
   
    @Override
    public List<Subject> findSubjectsBySemesterIdBetween(Integer fromSemester, Integer toSemester) {
        try {
            return sub.findSubjectsBySemesterIdBetween(fromSemester, toSemester);
        } catch (Exception e) {
            throw new ErrorHandler("Don't find any records");
        }    }

    @Override
    public Subject findSubjectBySubjectCode(String subjectCode) {
        return sub.findSubjectBySubjectCode(subjectCode).orElseThrow(() -> new ErrorHandler("Subject " + subjectCode + " is not existed"));
    }

    @Override
    public void save(Subject subject) {
        try {
            sub.save(subject);
        } catch (Exception e) {
            throw new ErrorHandler(e.getMessage());
        }    }

    @Override
    public void delete(int id) {
        try {
            sub.deleteById(id);
        } catch (Exception e) {
            throw new ErrorHandler(e.getMessage());
        }    }

    @Override
    public List<Subject> findAll() {
        try {
            return sub.findAll();
        } catch (Exception e) {
            throw new ErrorHandler(e.getMessage());
        }    }

    @Override
    public Subject findOne(int id) {
        return sub.findById(id).orElseThrow(() -> new ErrorHandler("do not found any subject with id: " + id));
    }

    
}
