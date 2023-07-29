/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.smart.webconfig;

import com.example.smart.api.service.AccountService;
import com.example.smart.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author PC
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    @Autowired
    private AccountService accountService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(accountService).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/subject/**","/api/provinces/**",
                 "/api/districts/**","/api/accounts/login","/api/wards/**"
                 ,"/api/semester/**","/api/roles/**","/fcm/**"
                        ,"/api/device/**","/api/room/**").permitAll()
                .antMatchers("/dashboard/login","/login/**","/dashboard/logout").permitAll()
                .antMatchers("/dashboard","/dashboard/view_room_active").hasAnyAuthority("ADMIN")
                .antMatchers("/dashboard/teacher/**","/dashboard/student/**"
                        ,"/dashboard/subject/**","/dashboard/news/**","/dashboard/class/**","/dashboard/attendance/**"
                        ,"/dashboard/room/**").hasAnyAuthority("ADMIN")
                .antMatchers("/dashboard/**").hasAnyAuthority("ADMIN")
                .antMatchers("/css/**","/js/**","/plugins/**","/img/**").permitAll()
                .antMatchers("/api/news/list","/api/news/get/{id}").permitAll()
                .antMatchers("/api/accounts/changePassword/{id}","/api/profiles/get/{id}","/api/teachers/get/{id}","/api/students/getByProfile/{id}").hasAnyAuthority("STUDENT","ADMIN","TEACHER")
//                .requestMatchers().hasAnyAuthority("STUDENT")
                .antMatchers("/api/teachers/getByProfile/{id}").hasAnyAuthority("TEACHER")
                .antMatchers("/api/students-subject/**","/api/classes/**","/api/schedules/**",
                        "/api/schedules_detail/**").hasAnyAuthority("ADMIN","STUDENT","TEACHER")
                .antMatchers("/api/accounts/**","/api/profiles/**","/api/students/**","/api/teachers/**",
                        "/api/staffs/**","/api/news/**").hasAnyAuthority("ADMIN")
                //teacher
                .antMatchers("/api/attendance/**","/dashboard/attendance/**"
                        ,"/api/student-class/**","/teacher/**","/api/students/**","/api/attendance_tracking/**")
                .permitAll()
                //student
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .logout()
                .logoutUrl("/dashboard/logout")
                .logoutSuccessUrl("/dashboard/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","_token")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","_token")
                .and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
