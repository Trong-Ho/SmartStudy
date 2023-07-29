/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IProvince;
import com.example.smart.api.repository.ProvinceRepository;
import com.example.smart.models.Province;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ProvinceService implements IProvince {

    @Autowired
    private ProvinceRepository repository;

    @Override
    public void save(Province province) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Province> findAll() {
        return repository.findAll();
    }

    @Override
    public Province findOne(int id) {
        return null;
    }
}
