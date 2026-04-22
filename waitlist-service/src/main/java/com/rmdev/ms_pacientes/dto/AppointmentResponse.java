package com.rmdev.ms_pacientes.dto;

import com.rmdev.ms_pacientes.model.AppointmentStatus;
import com.rmdev.ms_pacientes.model.AppointmentType;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO de respuesta con los datos públicos de una cita médica.
 */
public record AppointmentResponse(
        UUID id,
        UUID patientId,
        UUID doctorId,
        AppointmentType type,
        AppointmentStatus status,
        LocalDateTime scheduledAt,
        String notes,
        LocalDateTime createdAt
) {}
