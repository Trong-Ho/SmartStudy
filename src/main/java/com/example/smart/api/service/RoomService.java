/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IRoom;
import com.example.smart.api.repository.RoomRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Room;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class RoomService implements IRoom{
    @Autowired
    private RoomRepository repo;


    @Override
    public void save(Room room) {
        repo.save(room);
    }

    @Override
    public void delete(int id) {
      repo.deleteById(id);
    }

    @Override
    public List<Room> findAll() {
        return repo.findAll();
    }

    @Override
    public Room findOne(int id) {
        return repo.findById(id).orElseThrow(()->new ErrorHandler("Cannot find room with id = "+id));
    }
}
