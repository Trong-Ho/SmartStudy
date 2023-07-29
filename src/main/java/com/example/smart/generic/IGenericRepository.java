/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.smart.generic;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author PC
 */
@Component
public interface IGenericRepository<T> {

    void save(T t);

    void delete(int id);

    List<T> findAll();

    T findOne(int id);
}
