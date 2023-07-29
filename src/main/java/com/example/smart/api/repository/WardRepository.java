/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.Ward;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface WardRepository extends JpaRepository<Ward, Integer> {
    public List<Ward> findAllByProvinceIdAndDistrictId(Integer provinceId,Integer districtId);
}
