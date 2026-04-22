package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Cita de tipo Diagnóstico (exámenes, imágenes, laboratorio).
 */
@Entity
@DiscriminatorValue("DIAGNOSIS")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DiagnosisAppointment extends Appointment {

    /** Tipo de examen diagnóstico (ej. "Radiografía", "Resonancia Magnética", "Hemograma"). */
    @Column(length = 150)
    private String examType;

    /** Indica si el paciente debe venir en ayunas. */
    @Column
    private Boolean requiresFasting;
}
