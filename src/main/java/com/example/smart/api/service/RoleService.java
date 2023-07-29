/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IRole;
import com.example.smart.api.repository.RoleRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class RoleService implements IRole{
    @Autowired
    private RoleRepository repository;

    @Override
    public void save(Role role) {
        try {
            repository.save(role);
        }catch (Exception e){
            throw new ErrorHandler(e.getMessage());
        }
    }


    @Override
    public void delete(int id) {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new ErrorHandler(e.getMessage());
        }
    }

    @Override
    public List<Role> findAll() {
        try {
            return repository.findAll();
        }catch (Exception e){
            throw new ErrorHandler(e.getMessage());
        }

    }

    @Override
    public Role findOne(int id) {
        return repository.findById(id).orElseThrow(()->new ErrorHandler("Cannot find role with id = "+id));
    }

    public Role findByRoleName(String roleName){
        return repository.findRoleByRoleName(roleName).orElseThrow(()->new ErrorHandler("Cannot find role with name = "+roleName));
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return repository.findRoleByRoleName(roleName).orElseThrow(()->new ErrorHandler("Cannot find role with name = "+roleName));
    }
}
