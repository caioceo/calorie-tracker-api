package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.request.LoginRequest;
import dev.caio.fitsy.dto.request.RegisterUserRequest;
import dev.caio.fitsy.dto.response.LoginResponse;
import dev.caio.fitsy.dto.response.RegisterUserResponse;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){

        return authService.register(request);
    }

}
