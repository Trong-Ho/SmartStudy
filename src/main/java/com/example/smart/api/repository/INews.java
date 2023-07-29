/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.News;
import java.util.List;

/**
 *
 * @author PC
 */
public interface INews {

    List<News> findAll();

    News findById(Integer id);

    News save(News news);

    void deleteById(Integer id);
}
