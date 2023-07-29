/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.example.smart.api.service;

import com.example.smart.exception.ErrorHandler;
import com.example.smart.api.repository.AccountRepository;
import com.example.smart.api.repository.IAccount;
import com.example.smart.models.Account;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class AccountService implements IAccount, UserDetailsService{
    @Autowired
    AccountRepository repo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public void save(Account account) {
        try {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            repo.save(account);
        }catch (Exception e){
            throw new ErrorHandler("Cannot save data");
        }
    }
    
    @Override
    public void delete(int id) {

    }

    @Override
    public List<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Account findOne(int id) {
        return repo.findById(id).orElseThrow(()->new ErrorHandler("Can not find student with id = "+1));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repo.findAccountByUsername(username);
        if(account==null){
            throw new UsernameNotFoundException(username + " not found ");
        }
        else {
            List<GrantedAuthority> list = new ArrayList<>();
            GrantedAuthority authoritys
                    = new SimpleGrantedAuthority("ROLE_USER");
            list.add(authoritys);
            return new User(account.getUsername(),
                    account.getPassword(), list);
        }
    }
    
    
}
