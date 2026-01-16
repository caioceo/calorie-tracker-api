package dev.caio.fitsy.service;

import dev.caio.fitsy.config.security.TokenConfig;
import dev.caio.fitsy.dto.request.LoginRequest;
import dev.caio.fitsy.dto.request.RegisterUserRequest;
import dev.caio.fitsy.dto.response.LoginResponse;
import dev.caio.fitsy.dto.response.RegisterUserResponse;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    public ResponseEntity<LoginResponse> login (LoginRequest request){

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    public ResponseEntity<RegisterUserResponse> register(RegisterUserRequest request){

        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email já cadastrado");
        }

        User newUser = new User();

        newUser.setEmail(request.email());
        newUser.setSenha(passwordEncoder.encode(request.senha()));
        newUser.setNome(request.nome());

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getNome(), newUser.getEmail(), HttpStatus.CREATED.value()));
    }
}
