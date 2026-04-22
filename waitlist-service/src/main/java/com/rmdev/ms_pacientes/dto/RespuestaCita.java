package com.rmdev.ms_pacientes.dto;

import com.rmdev.ms_pacientes.model.EstadoCita;
import com.rmdev.ms_pacientes.model.TipoCita;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO de respuesta con los datos públicos de una cita médica.
 */
public record RespuestaCita(
        UUID id,
        UUID pacienteId,
        UUID medicoId,
        TipoCita tipo,
        EstadoCita estado,
        LocalDateTime fechaProgramada,
        String observaciones,
        LocalDateTime creadoEn
) {}
