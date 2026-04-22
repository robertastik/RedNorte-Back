package com.rmdev.ms_pacientes.model;

/**
 * Estados posibles de una cita médica en el ciclo de vida del waitlist-service.
 */
public enum EstadoCita {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA,
    COMPLETADA,
    NO_ASISTIO
}
