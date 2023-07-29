/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IClassSubject;
import com.example.smart.dto.ResponseModel;
import com.example.smart.generic.GenericController;
import com.example.smart.models.ClassSubject;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/class-subject")
public class ClassSubjectRestController extends GenericController<ClassSubject>{
    private final IClassSubject service;

    @Autowired
    public ClassSubjectRestController(IClassSubject service) {
        this.service = service;
    }

    @GetMapping("/findClassSubjectByClassIdAndSubjectId/{classId}/{subjectId}")
    ResponseEntity<?> findClassSubjectByClassIdAndSubjectId(@PathVariable("classId")int classId,
                                                            @PathVariable("subjectId")int subjectId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Success", LocalTime.now().toString(), service.findClassSubjectByClassIdAndSubjectId(classId, subjectId)));
    }
}
