/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.INews;
import com.example.smart.api.repository.NewsRepository;
import com.example.smart.models.News;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class NewsService implements INews{
    
    @Autowired
    NewsRepository repository;

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public News findById(Integer id) {
        Optional<News> news = repository.findById(id);
        return news.orElse(null);
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
