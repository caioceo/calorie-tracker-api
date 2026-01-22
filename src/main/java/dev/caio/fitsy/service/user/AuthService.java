package dev.caio.fitsy.service.user;

import dev.caio.fitsy.config.security.TokenConfig;
import dev.caio.fitsy.dto.mapper.UserRegisterMapper;
import dev.caio.fitsy.dto.request.auth.LoginRequest;
import dev.caio.fitsy.dto.request.auth.RegisterUserRequest;
import dev.caio.fitsy.dto.response.auth.LoginResponse;
import dev.caio.fitsy.dto.response.auth.RegisterUserResponse;
import dev.caio.fitsy.exceptions.AlreadyExistsException;
import dev.caio.fitsy.exceptions.NotFoundException;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenConfig tokenConfig, UserRegisterMapper userRegisterMapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.userRegisterMapper = userRegisterMapper;
    }

    public String login (LoginRequest request){

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        if(user == null){
            throw new NotFoundException("Token", "user", null);
        }
        String token = tokenConfig.generateToken(user);

        return token;
    }

    public User register(RegisterUserRequest request){

        if(userRepository.existsByEmail(request.email())){
            throw new AlreadyExistsException("Email");
        }

        User newUser = userRegisterMapper.createRequestToModel(request);

        userRepository.save(newUser);

        return newUser;
    }
}
