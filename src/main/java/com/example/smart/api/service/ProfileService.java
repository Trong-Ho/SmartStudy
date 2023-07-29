/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.api.repository.IProfile;
import com.example.smart.api.repository.ProfileRepository;
import com.example.smart.exception.ErrorHandler;
import com.example.smart.models.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class ProfileService {
    
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // Lấy danh sách tất cả các Profile
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // Lấy Profile theo id
    public Profile getProfileById(Integer id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        return optionalProfile.orElse(null);
    }

    // Thêm mới một Profile
    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    // Cập nhật thông tin một Profile
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    // Xóa một Profile
    public void deleteProfile(Integer id) {
        profileRepository.deleteById(id);
    }
  
    // Lấy Profile theo accountId
    public Profile getProfileByAccountId(Integer id) {
        Optional<Profile> optionalProfile = profileRepository.findProfileByAccountId(id);
        return optionalProfile.orElse(null);
    }

    // Lấy Profile theo identityCard
    public Profile getProfileByIdentityCard(String card) {
        return profileRepository.findProfileByIdentityCard(card);
    }
    
}
