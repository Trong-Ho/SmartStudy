/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.ISemester;
import com.example.smart.api.repository.SemesterRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Semester;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class SemesterService implements ISemester{
    @Autowired
    private SemesterRepository repo;

    @Override
    public void save(Semester semester) {
        try {
            repo.save(semester);
        } catch (Exception e) {
            throw new ErrorHandler("Backup failed");
        }    }

    @Override
    public void delete(int id) {
       try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new ErrorHandler("Can not find ID : " + id + " to delete");
        }    }

    @Override
    public List<Semester> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new ErrorHandler("Không có dữ liệu");
        }    }

    @Override
    public Semester findOne(int id) {
       try {
            return repo.findById(id).get();
        } catch (Exception e) {
            throw new ErrorHandler("Data cannot be found with ID : " + id);
        }    }
}
