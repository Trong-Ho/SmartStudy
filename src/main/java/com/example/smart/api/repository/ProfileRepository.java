/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.smart.api.repository;

import com.example.smart.models.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findProfileByAccountId(Integer id);

    Profile findProfileByIdentityCard(String card);
}
