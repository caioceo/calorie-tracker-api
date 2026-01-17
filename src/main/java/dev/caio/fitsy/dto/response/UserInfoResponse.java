package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Sexo;

import java.time.LocalDate;

public record UserInfoResponse(Integer status,
                               String mensagem,
                               Float peso,
                               Float altura,
                               Sexo sexo,
                               LocalDate data_nascimento) {
}
