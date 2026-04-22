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
@DiscriminatorValue("CIRUGIA")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CitaCirugia extends Cita {

    /** Nombre del procedimiento quirúrgico a realizar. */
    @Column(length = 200)
    private String nombreProcedimiento;

    /** Indica si requiere anestesia general. */
    @Column
    private Boolean requiereAnestesiaGeneral;

    /** Duración estimada de la cirugía en minutos. */
    @Column
    private Integer duracionEstimadaMinutos;
}
