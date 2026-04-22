package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Cita de tipo Consulta médica general.
 */
@Entity
@DiscriminatorValue("CONSULTATION")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ConsultationAppointment extends Appointment {

    /** Especialidad médica requerida para la consulta. */
    @Column(length = 100)
    private String specialty;

    /** Indica si es una consulta de seguimiento de una cita previa. */
    @Column
    private Boolean isFollowUp;
}
