/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.WardRepository;
import com.example.smart.models.Ward;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class WardService {
    @Autowired
    private WardRepository repository;

    public List<Ward>findAllByProvinceAndDistrict(Integer provinceId,Integer districtId){
        return  repository.findAllByProvinceIdAndDistrictId(provinceId,districtId);
    }
}
