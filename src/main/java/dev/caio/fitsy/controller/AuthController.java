package dev.caio.fitsy.controller;

import dev.caio.fitsy.config.security.TokenService;
import dev.caio.fitsy.dto.auth.AuthLoginRequest;
import dev.caio.fitsy.dto.auth.LoginResponse;
import dev.caio.fitsy.dto.auth.RegisterRequest;
import dev.caio.fitsy.model.Client.Client;
import dev.caio.fitsy.model.Enum.Role;
import dev.caio.fitsy.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthLoginRequest dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate((usernamePassword));

        var token = tokenService.generateToken((Client) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register (@Valid @RequestBody RegisterRequest dto) {
        if (clientRepository.findByEmail(dto.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        Client new_client = new Client();
        new_client.setName(dto.name());
        new_client.setEmail(dto.email());
        new_client.setHeight(dto.height());
        new_client.setWeight(dto.weight());
        new_client.setBirthDate(dto.birthDate());

        new_client.setPassword(encryptedPassword);
        new_client.setRole(Role.FREEMIUM);

        clientRepository.save(new_client);
        return ResponseEntity.ok().build();
    }
}
