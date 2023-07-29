/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.service.ProfileService;
import com.example.smart.models.Profile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {

    private final ProfileService profileService;

    @Autowired
    public ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Lấy danh sách tất cả các Profile
    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    // Lấy Profile theo id
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Integer id) {
        return profileService.getProfileById(id);
    }

    // Thêm mới một Profile
    @PostMapping
    public Profile addProfile(@RequestBody Profile profile) {
        return profileService.addProfile(profile);
    }

    // Cập nhật thông tin một Profile
//    @PutMapping("/{id}")
//    public Profile updateProfile(@PathVariable Integer id, @RequestBody Profile profile) {
//        Profile existingProfile = profileService.getProfileById(id);
//        if (existingProfile == null) {
//            throw new ResourceNotFoundException("Profile", "id", id);
//        }
//        profile.setId(id);
//        return profileService.updateProfile(profile);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Integer id, @RequestBody Profile profile) {
        Profile existingProfile = profileService.getProfileById(id);
        if (existingProfile == null) {
            return ResponseEntity.notFound().build();
        }
        profile.setId(id);
        return ResponseEntity.ok(profileService.updateProfile(profile));
    }

    // Xóa một Profile
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Integer id) {
        Profile existingProfile = profileService.getProfileById(id);
        if (existingProfile == null) {
            return ResponseEntity.notFound().build();
        }
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteProfile(@PathVariable Integer id) {
//        Profile existingProfile = profileService.getProfileById(id);
//        if (existingProfile == null) {
//            throw new ResourceNotFoundException("Profile", "id", id);
//        }
//        profileService.deleteProfile(id);
//        return ResponseEntity.ok().build();
//    }

    // Lấy Profile theo accountId
    @GetMapping("/by-account/{accountId}")
    public Profile getProfileByAccountId(@PathVariable Integer accountId) {
        return profileService.getProfileByAccountId(accountId);
    }

    // Lấy Profile theo identityCard
    @GetMapping("/by-identity-card/{identityCard}")
    public Profile getProfileByIdentityCard(@PathVariable String identityCard) {
        return profileService.getProfileByIdentityCard(identityCard);
    }
}
