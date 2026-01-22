package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.request.auth.RegisterUserRequest;
import dev.caio.fitsy.dto.response.auth.RegisterUserResponse;
import dev.caio.fitsy.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserRegisterMapper {

    private final PasswordEncoder passwordEncoder;

    public UserRegisterMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponse modelToResponse(User user, Integer status){
        return new RegisterUserResponse(user.getNome(), user.getEmail(), status);
    }

    public User createRequestToModel(RegisterUserRequest request){
        User user = new User();
        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setSenha(passwordEncoder.encode(request.senha()));
        return user;
    }
}
