package com.rmdev.ms_pacientes.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para crear una cita de tipo Cirugía.
 */
public record SolicitudCirugia(
        @NotNull UUID pacienteId,
        @NotNull UUID medicoId,
        @NotNull @FutureOrPresent LocalDateTime fechaProgramada,
        String observaciones,
        @NotNull String nombreProcedimiento,
        Boolean requiereAnestesiaGeneral,
        @Positive Integer duracionEstimadaMinutos
) {}
