package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.mapper.UserRegisterMapper;
import dev.caio.fitsy.dto.request.auth.LoginRequest;
import dev.caio.fitsy.dto.request.auth.RegisterUserRequest;
import dev.caio.fitsy.dto.response.auth.RegisterUserResponse;
import dev.caio.fitsy.service.user.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRegisterMapper userRegisterMapper;

    public AuthController(AuthService authService, UserRegisterMapper userRegisterMapper) {
        this.userRegisterMapper = userRegisterMapper;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterMapper.modelToResponse(authService.register(request), HttpStatus.CREATED.value()));
    }

}
