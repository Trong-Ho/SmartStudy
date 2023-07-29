/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.smart.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author PC
 */
public class CrytalPasswordUtils {
    public static String encrytPassword(String pass){
        BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
        return bcpe.encode(pass);
    }
    public static void main(String[] args) {
       // System.out.println(encrytPassword("123"));
    }
}
