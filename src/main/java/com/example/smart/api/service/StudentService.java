/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IStudent;
import com.example.smart.api.repository.StudentRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class StudentService implements IStudent{
    @Autowired
    private StudentRepository repository;
    List<Student> listStudent;

    @Override
    public Student getByProfileId(Integer id) {
        return repository.findStudentByProfileId(id).orElseThrow(() -> new ErrorHandler("Cannot find student with profile id := " + id));
    }

    @Override
    public Student findStudentByStudentCard(String studentCard) {
        return repository.findStudentByStudentCard(studentCard).orElseThrow(() -> new ErrorHandler("Don't find student with student card: " + studentCard));
    }

    @Override
    public Student findStudentCard(String studentCard) {
        return repository.findStudentByStudentCard(studentCard).orElse(null);
    }

    @Override
    public List<Student> findStudentIdByRangeStudentCard(List<String> listStudentCard) {
        List<Student> listResult = new ArrayList<>();
        for (String stringCard : listStudentCard) {
            Optional<Student> student = repository.findStudentByStudentCard(stringCard);
            if (student.isPresent())
                listResult.add(student.get());
            else throw new ErrorHandler("Don't find student with student card: " + stringCard);
        }
        return listResult;
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Student findOne(int id) {
        return repository.findById(id).orElseThrow(() -> new ErrorHandler("Cannot find student with id := " + id));
    }

    
}
