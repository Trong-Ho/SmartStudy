/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/districts")
public class DistrictRestController {
    @Autowired
    private DistrictService service;

    @GetMapping("/")
    public ResponseEntity<?> findAll(@RequestParam("province") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByProvince(id));
    }
}
