package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DIAGNOSTICO")
public class CitaDiagnostico extends Cita {

    @Column(length = 150)
    private String tipoExamen;

    @Column
    private Boolean requiereAyuno;

    public String getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(String tipoExamen) { this.tipoExamen = tipoExamen; }
    public Boolean getRequiereAyuno() { return requiereAyuno; }
    public void setRequiereAyuno(Boolean requiereAyuno) { this.requiereAyuno = requiereAyuno; }
}
