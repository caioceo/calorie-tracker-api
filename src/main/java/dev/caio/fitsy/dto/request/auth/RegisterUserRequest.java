package dev.caio.fitsy.dto.request.auth;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
        @NotEmpty(message = "Nome é obrigatorio") String nome,
        @NotEmpty(message = "Email é obrigatorio") String email,
        @NotEmpty(message = "Senha é obrigatorio") String senha
    ){
}
