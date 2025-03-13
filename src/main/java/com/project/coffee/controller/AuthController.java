package com.project.coffee.controller;

import com.project.coffee.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("password")) {
            String token = jwtUtil.generateToken(username, "ADMIN");
            return ResponseEntity.ok(token);
        } else if (username.equals("user") && password.equals("password")) {
            String token = jwtUtil.generateToken(username, "USER");
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
