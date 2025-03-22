package com.college.sri.eshwar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.college.sri.eshwar.security.JwtUtil;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token/{username}")
    public String generateToken(@PathVariable String username) {
        return jwtUtil.generateToken(username);
    }
}