package com.rmdev.ms_pacientes.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para crear una cita de tipo Consulta.
 */
public record SolicitudConsulta(
        @NotNull UUID pacienteId,
        @NotNull UUID medicoId,
        @NotNull @FutureOrPresent LocalDateTime fechaProgramada,
        String observaciones,
        @NotNull String especialidad,
        Boolean esSeguimiento
) {}
