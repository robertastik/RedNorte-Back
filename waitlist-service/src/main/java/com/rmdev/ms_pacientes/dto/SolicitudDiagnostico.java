package com.rmdev.ms_pacientes.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para crear una cita de tipo Diagnóstico.
 */
public record SolicitudDiagnostico(
        @NotNull UUID pacienteId,
        @NotNull UUID medicoId,
        @NotNull @FutureOrPresent LocalDateTime fechaProgramada,
        String observaciones,
        @NotNull String tipoExamen,
        Boolean requiereAyuno
) {}
