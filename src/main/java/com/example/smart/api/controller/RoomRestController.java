/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IRoom;
import com.example.smart.models.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/room")
public class RoomRestController {
    @Autowired
    private IRoom iRoom;

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iRoom.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id")Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(iRoom.findOne(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> saveRoom(@RequestParam("room") String roomJson) throws Exception {
        Room room = new ObjectMapper().readValue(roomJson,Room.class);
        iRoom.save(room);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
