package dev.caio.fitsy.dto.request;

import dev.caio.fitsy.model.enums.Sexo;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserInfoRequest(
        @NotNull(message = "Peso é obrigatório") @Positive(message = "Valor deve ser positivo") @Min(30) @Max(400) Float peso,
        @NotNull(message = "Altura é obrigatória") @Positive(message = "Valor deve ser positivo") @Min(1) @Max(3) Float altura,
        @NotNull(message = "Sexo é obrigatório") Sexo sexo,
        @Past @NotNull(message = "Data de nascimento é obrigatório") LocalDate data_nascimento){
}
