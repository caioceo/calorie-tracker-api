package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.request.auth.LoginRequest;
import dev.caio.fitsy.dto.request.auth.RegisterUserRequest;
import dev.caio.fitsy.dto.response.auth.LoginResponse;
import dev.caio.fitsy.dto.response.auth.RegisterUserResponse;
import dev.caio.fitsy.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

}
