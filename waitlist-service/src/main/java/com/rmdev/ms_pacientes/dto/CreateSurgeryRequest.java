package com.rmdev.ms_pacientes.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para crear una cita de tipo Cirugía.
 */
public record CreateSurgeryRequest(
        @NotNull UUID patientId,
        @NotNull UUID doctorId,
        @NotNull @FutureOrPresent LocalDateTime scheduledAt,
        String notes,
        @NotNull String procedureName,
        Boolean requiresGeneralAnesthesia,
        @Positive Integer estimatedDurationMinutes
) {}
