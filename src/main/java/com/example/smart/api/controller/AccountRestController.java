/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.smart.api.controller;

import com.example.smart.api.repository.IAccount;
import com.example.smart.dto.LoginResponse;
import com.example.smart.dto.ResponseModel;
import com.example.smart.generic.GenericController;
import com.example.smart.jwt.JwtTokenProvider;
import com.example.smart.models.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class AccountRestController extends GenericController<Account> {

    @Autowired
    IAccount iaccount;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/save")
    public ResponseEntity<?> postAccount(@RequestParam("account") String account)
            throws JsonProcessingException {
        Account accountConvert = new ObjectMapper()
                .readValue(account, Account.class);
        iaccount.save(accountConvert);
        return new ResponseEntity<>(
                new ResponseModel("success", LocalTime.now().toString(), accountConvert),
                HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        try {
            log.info(
                    "START method login params username = {}, password = {} :::::::::",
                    username,
                    password
            );

            org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Account account = (Account) authentication.getPrincipal();
            String jwtToken = jwtTokenProvider.generateTokenFromAccount(account);
            log.info("Response account = {} , token = {}", account, jwtToken);
            log.info("FINISH method login :::::::::");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new LoginResponse("success", jwtToken, account));
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("FAILED method login :::::::::");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new LoginResponse(null, null, "failed"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAccount(@PathVariable("id") Integer id) {
        Account account = iaccount.findOne(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("changePassword/{id}")
    public ResponseEntity<?> changePasswordAccount(
            @PathVariable("id") Integer id,
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword
    ) {
        try {
            log.info(
                    "START method change password width params id= {},password ={}, newPassword= {} ",
                    id,
                    password,
                    newPassword
            );
            Account account = iaccount.findOne(id);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account, password)
            );
            Account getAccount = (Account) authentication.getPrincipal();
            getAccount.setPassword(newPassword);
            iaccount.save(getAccount);
            log.info("FINISH method changePasswordAccount :::::::::");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new ResponseModel("success", LocalTime.now().toString(), getAccount)
                    );
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResponseModel(e.getMessage(), LocalTime.now().toString(), null)
                    );
        }
    }

    @PutMapping("reset_password/{id}")
    public ResponseEntity<?> reset_password(
            @PathVariable("id") Integer id,
            @RequestParam("password") String password
    ) {
        try {
            log.info(
                    "START method reset_password width params id= {},password ={}",
                    id,
                    password
            );
            Account account = iaccount.findOne(id);
            account.setPassword(password);
            iaccount.save(account);
            log.info("FINISH method changePasswordAccount :::::::::");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            new ResponseModel("success", LocalTime.now().toString(), account)
                    );
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResponseModel(e.getMessage(), LocalTime.now().toString(), null)
                    );
        }
    }

}
