package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.enums.Status;

import java.time.LocalDate;

public record HistoricoPesoResponse(Status status, LocalDate date_registro, Float peso) {
}
