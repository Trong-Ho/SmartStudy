/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IMark;
import com.example.smart.api.repository.MarkRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Mark;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
@Slf4j
public class MarkService implements IMark{

    @Autowired
    private MarkRepository dao;
    
    @Override
    public void saveAll(List<Mark> markList) {
        dao.saveAll(markList);
    }

    @Override
    public Mark findMarkByStudentSubjectId(int id) {
        dao.findFirstByStudentSubjectId(id);
        if (dao.findFirstByStudentSubjectId(id).isPresent()){
            return dao.findFirstByStudentSubjectId(id).get();
        }else{
            return null;
        }    }

    @Override
    public void save(Mark mark) {
        dao.save(mark);
    }

    @Override
    public void delete(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Mark> findAll() {
        return dao.findAll();
    }

    @Override
    public Mark findOne(int id) {
        return dao.findById(id).orElseThrow(()-> new ErrorHandler("Cannot find any record"));
    }
    
}
