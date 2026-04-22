package com.rmdev.ms_pacientes.model;

/**
 * Tipos de cita médica soportados por el sistema.
 * Utilizados por el Factory Method para la instanciación correcta.
 */
public enum AppointmentType {
    CONSULTATION,
    SURGERY,
    DIAGNOSIS
}
