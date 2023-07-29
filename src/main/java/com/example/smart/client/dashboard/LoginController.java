/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.smart.client.dashboard;

import com.example.smart.dto.LoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author PC
 */
@Controller
@Slf4j
public class LoginController {

  private final String url = "http://localhost:9999/api/accounts/";

  AuthenticationManager authenticationManager;

  @GetMapping("dashboard/login")
  public String login() {
    return "dashboard/login/login";
  }

  @PostMapping("dashboard/login")
  public String login(
    @RequestParam("username") String username,
    @RequestParam("password") String password,
    HttpServletResponse responseHttp,
    HttpServletRequest requestHttp,
    Model model
  ) throws JsonProcessingException {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("username", username);
    params.add("password", password);
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
      params,
      headers
    );
    try {
      ResponseEntity<LoginResponse> response = restTemplate.exchange(
        url + "login",
        HttpMethod.POST,
        request,
        LoginResponse.class
      );
      String responseJson = new ObjectMapper()
        .writeValueAsString(response.getBody().getData());
      String _token = response.getBody().getToken();

      UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
        username,
        password
      );
      Authentication auth = authenticationManager.authenticate(authReq);
      SecurityContext sc = SecurityContextHolder.getContext();
      sc.setAuthentication(auth);
      HttpSession session = requestHttp.getSession(true);
      session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

      boolean hasUserRole = auth
        .getAuthorities()
        .stream()
        .anyMatch(r ->
          r.getAuthority().equals("ADMIN") || r.getAuthority().equals("STAFF")
        );

      if (hasUserRole) {
        Cookie jwtTokenCookie = new Cookie("_token", _token);
        jwtTokenCookie.setSecure(true);
        jwtTokenCookie.setHttpOnly(true);
        jwtTokenCookie.setPath("/");
        responseHttp.addCookie(jwtTokenCookie);
        return "redirect:/dashboard";
      } else {
        model.addAttribute("msg", "Login fail");
        return "dashboard/login/login";
      }
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        model.addAttribute("msg", "Login fail");
        return "dashboard/login/login";
      }
      return null;
    }
  }
}
