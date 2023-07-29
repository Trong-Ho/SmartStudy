/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.smart.dto;

import java.util.Date;
import lombok.*;

/**
 *
 * @author PC
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private int id;
    private String firstname;
    private String lastname;
    private Date dob;
    private String address;
    private String phone;
    private String email;
    private String avatarUrl;
    private String sex;
}
