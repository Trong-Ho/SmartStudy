/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.ClassSubjectRepository;
import com.example.smart.api.repository.IClassSubject;
import com.example.smart.models.ClassSubject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ClassSubjectService implements IClassSubject {

    @Autowired
    private ClassSubjectRepository repository;

    @Override
    public ClassSubject findClassSubjectByClassIdAndSubjectId(int classId, int subjectId) {
        return repository.findClassSubjectByClassIdAndSubjectId(classId, subjectId).orElse(null);
    }

    @Override
    public void save(ClassSubject classSubject) {
        repository.save(classSubject);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<ClassSubject> findAll() {
        return repository.findAll();
    }

    @Override
    public ClassSubject findOne(int id) {
        return repository.findById(id).orElse(null);
    }

}
