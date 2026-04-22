package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Cita de tipo Cirugía.
 */
@Entity
@DiscriminatorValue("SURGERY")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SurgeryAppointment extends Appointment {

    /** Nombre del procedimiento quirúrgico a realizar. */
    @Column(length = 200)
    private String procedureName;

    /** Indica si requiere sala de operaciones con anestesia general. */
    @Column
    private Boolean requiresGeneralAnesthesia;

    /** Duración estimada de la cirugía en minutos. */
    @Column
    private Integer estimatedDurationMinutes;
}
