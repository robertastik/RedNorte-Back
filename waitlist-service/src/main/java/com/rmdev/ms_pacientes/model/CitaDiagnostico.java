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
@DiscriminatorValue("DIAGNOSTICO")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CitaDiagnostico extends Cita {

    /** Tipo de examen diagnóstico (ej. "Radiografía", "Resonancia Magnética"). */
    @Column(length = 150)
    private String tipoExamen;

    /** Indica si el paciente debe venir en ayunas. */
    @Column
    private Boolean requiereAyuno;
}
