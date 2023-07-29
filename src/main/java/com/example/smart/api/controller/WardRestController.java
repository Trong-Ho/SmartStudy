/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/wards")
public class WardRestController {
    @Autowired
    private WardService service;
    @GetMapping("/")
    public ResponseEntity<?> findAllByProvinceAndDistrict(@RequestParam("province") Integer provinceId,
                                                          @RequestParam("district") Integer districtId){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByProvinceAndDistrict(provinceId,districtId));
    }
}
