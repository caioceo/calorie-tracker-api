package dev.caio.fitsy.dto.auth;

import java.time.LocalDate;

public record RegisterRequest(String email, String name, String password, Double height, Double weight, LocalDate birthDate) {
}
