/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.GenericRepository;
import com.example.smart.models.Role;

import java.util.Optional;

public interface RoleRepository extends GenericRepository<Role,Integer> {
    public Optional<Role> findRoleByRoleName(String roleName);
}
