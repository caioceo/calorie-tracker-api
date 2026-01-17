package dev.caio.fitsy.dto.response;

import java.time.LocalDate;

public record HistoricoPesoResponse(Float peso, LocalDate date_registro) {
}
