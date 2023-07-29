/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.DistrictRepository;
import com.example.smart.models.District;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class DistrictService {

    @Autowired
    private DistrictRepository repository;

    public List<District> findAllByProvince(Integer provinceId) {
        return repository.findAll().stream()
                .filter(district -> Objects.equals(district.getProvinceId(), provinceId))
                .toList();
    }
}
