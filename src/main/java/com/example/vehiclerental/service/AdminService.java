package com.example.vehiclerental.service;

import com.example.vehiclerental.dto.AdminLoginDTO;
import com.example.vehiclerental.entity.Admin;
import com.example.vehiclerental.exception.UnauthorizedException;
import com.example.vehiclerental.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Simple admin login: checks username and password match.
     * For academic/demo purposes — no encryption, no JWT.
     */
    public Admin login(AdminLoginDTO loginDTO) {
        Optional<Admin> admin = adminRepository.findByUsernameAndPassword(
                loginDTO.getUsername(), loginDTO.getPassword());

        if (admin.isEmpty()) {
            throw new UnauthorizedException("Invalid username or password");
        }

        return admin.get();
    }
}
