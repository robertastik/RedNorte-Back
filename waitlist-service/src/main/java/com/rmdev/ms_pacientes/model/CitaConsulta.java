package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSULTA")
public class CitaConsulta extends Cita {

    @Column(length = 100)
    private String especialidad;

    @Column
    private Boolean esSeguimiento;

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public Boolean getEsSeguimiento() { return esSeguimiento; }
    public void setEsSeguimiento(Boolean esSeguimiento) { this.esSeguimiento = esSeguimiento; }
}
