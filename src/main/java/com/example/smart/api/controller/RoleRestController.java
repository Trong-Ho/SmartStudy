/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IRole;
import com.example.smart.dto.ResponseModel;
import com.example.smart.models.Role;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {
    @Autowired
    private IRole iRole;

    @PostMapping("/")
    public ResponseEntity<?> saveStudent(@RequestBody Role role) {
        iRole.save(role);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("success", LocalTime.now().toString(),role));
    }


    @GetMapping("/list")
    public ResponseEntity<?> findAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(iRole.findAll());
    }

    @PostMapping("/get")
    public ResponseEntity<?> getOneRole(@RequestParam("role")String role){
        return ResponseEntity.status(HttpStatus.OK).body(iRole.findRoleByRoleName(role));
    }
}
