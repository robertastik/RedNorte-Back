package com.rmdev.ms_pacientes.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para crear una cita de tipo Consulta.
 */
public record CreateConsultationRequest(
        @NotNull UUID patientId,
        @NotNull UUID doctorId,
        @NotNull @FutureOrPresent LocalDateTime scheduledAt,
        String notes,
        @NotNull String specialty,
        Boolean isFollowUp
) {}
