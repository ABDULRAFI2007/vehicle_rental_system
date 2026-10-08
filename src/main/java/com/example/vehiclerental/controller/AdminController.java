package com.example.vehiclerental.controller;

import com.example.vehiclerental.dto.AdminLoginDTO;
import com.example.vehiclerental.entity.Admin;
import com.example.vehiclerental.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * POST /api/admin/login - Admin login
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody AdminLoginDTO loginDTO) {
        Admin admin = adminService.login(loginDTO);
        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "username", admin.getUsername(),
                "authenticated", true
        ));
    }
}
