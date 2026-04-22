package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Cita de tipo Consulta médica.
 */
@Entity
@DiscriminatorValue("CONSULTA")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CitaConsulta extends Cita {

    /** Especialidad médica requerida para la consulta. */
    @Column(length = 100)
    private String especialidad;

    /** Indica si es una consulta de seguimiento de una cita previa. */
    @Column
    private Boolean esSeguimiento;
}
