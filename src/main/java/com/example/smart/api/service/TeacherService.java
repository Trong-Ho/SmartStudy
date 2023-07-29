/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.ITeacher;
import com.example.smart.api.repository.TeacherRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Teacher;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class TeacherService implements ITeacher{
    @Autowired
    private TeacherRepository repo;

    @Override
    public Teacher findTeacherByProfileId(Integer id) {
        return repo.findTeacherByProfileId(id).orElseThrow(()->new ErrorHandler("Cannot find teacher with profileId := "+id));
    }

    @Override
    public Teacher findTeacherByCard(String card) {
        return repo.findTeacherByTeacherCard(card).orElseThrow(()->new ErrorHandler("Cannot find teacher with card = "+card));
    }

    @Override
    public void save(Teacher teacher) {
        repo.save(teacher);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return repo.findAll();
    }

    @Override
    public Teacher findOne(int id) {
        return repo.findById(id).orElseThrow(()->new ErrorHandler("Cannot find teacher with id := "+id));
    }
}
