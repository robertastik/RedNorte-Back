package com.rmdev.ms_pacientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CIRUGIA")
public class CitaCirugia extends Cita {

    @Column(length = 200)
    private String nombreProcedimiento;

    @Column
    private Boolean requiereAnestesiaGeneral;

    @Column
    private Integer duracionEstimadaMinutos;

    public String getNombreProcedimiento() { return nombreProcedimiento; }
    public void setNombreProcedimiento(String nombreProcedimiento) { this.nombreProcedimiento = nombreProcedimiento; }
    public Boolean getRequiereAnestesiaGeneral() { return requiereAnestesiaGeneral; }
    public void setRequiereAnestesiaGeneral(Boolean requiereAnestesiaGeneral) { this.requiereAnestesiaGeneral = requiereAnestesiaGeneral; }
    public Integer getDuracionEstimadaMinutos() { return duracionEstimadaMinutos; }
    public void setDuracionEstimadaMinutos(Integer duracionEstimadaMinutos) { this.duracionEstimadaMinutos = duracionEstimadaMinutos; }
}
