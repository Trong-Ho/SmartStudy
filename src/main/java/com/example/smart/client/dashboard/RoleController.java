/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.smart.client.dashboard;

import com.example.smart.dto.ResponseModel;
import com.example.smart.jwt.JWTUtils;
import com.example.smart.models.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping("dashboard/role/")
@Slf4j
public class RoleController {
    
    private final String url = "http://localhost:9999/api/roles/";

    @GetMapping("index-role")
    public String index(Model model,@CookieValue(name = "_token", defaultValue = "") String _token) throws JsonProcessingException {
        try {
            JWTUtils.checkExpired(_token);
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            List<Role> listRole = restTemplate.getForObject(url+"list",List.class);
            String json = objectMapper.writeValueAsString(listRole);
            List<Role> listRoleConvert = objectMapper.readValue(json, new TypeReference<List<Role>>() {});
            model.addAttribute("listRole",listRoleConvert.stream().filter(role -> !role.getRoleName().equals("ADMIN") &&!role.getRoleName().equals("STUDENT")&&!role.getRoleName().equals("TEACHER") ));
            return "/dashboard/role/role_index";
        }catch (Exception ex){
            log.error(ex.getMessage());
            return "redirect:/dashboard/logout";
        }
    }

    @PostMapping("create_role")
    @ResponseBody
    public Object create_role(@RequestBody Role role,@CookieValue(name = "_token", defaultValue = "") String _token){
        try {
            JWTUtils.checkExpired(_token);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> content = new LinkedMultiValueMap<>();
            content.add("roleName",role.getRoleName());
            content.add("roleDescription",role.getRoleDescription());
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(content, headers);
            ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, ResponseModel.class);
            return response;
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ex.getMessage();
        }
    }
    
}
