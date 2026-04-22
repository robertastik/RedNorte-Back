package com.rmdev.ms_pacientes.model;

/**
 * Tipos de cita médica soportados por el sistema.
 * Utilizados por el patrón Factory Method para la instanciación correcta.
 */
public enum TipoCita {
    CONSULTA,
    CIRUGIA,
    DIAGNOSTICO
}
