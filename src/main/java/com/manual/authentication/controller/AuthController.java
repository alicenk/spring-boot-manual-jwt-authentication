package com.manual.authentication.controller;

import com.manual.authentication.dto.AuthRequestDTO;
import com.manual.authentication.dto.AuthResponseDTO;
import com.manual.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> autnhenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.authenticate(authRequestDTO));
    }
}
