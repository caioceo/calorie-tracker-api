package dev.caio.fitsy.dto.response;

import jakarta.validation.constraints.NotEmpty;

public record LoginResponse(String token) {
}
