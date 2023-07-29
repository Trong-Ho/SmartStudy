/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.generic.IGenericRepository;
import com.example.smart.models.Role;

public interface IRole extends IGenericRepository<Role> {
    Role findRoleByRoleName(String roleName);
}
