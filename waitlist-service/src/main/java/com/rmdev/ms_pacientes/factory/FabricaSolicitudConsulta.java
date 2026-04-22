package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.dto.SolicitudConsulta;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.CitaConsulta;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudConsulta implements FabricaSolicitudMedica {

    @Override
    public Cita crear(Object solicitud) {
        if (!(solicitud instanceof SolicitudConsulta dto)) {
            throw new IllegalArgumentException("Se esperaba un SolicitudConsulta");
        }
        CitaConsulta cita = new CitaConsulta();
        cita.setPacienteId(dto.pacienteId());
        cita.setMedicoId(dto.medicoId());
        cita.setFechaProgramada(dto.fechaProgramada());
        cita.setObservaciones(dto.observaciones());
        cita.setEspecialidad(dto.especialidad());
        cita.setEsSeguimiento(dto.esSeguimiento());
        return cita;
    }

    @Override
    public TipoCita getTipo() {
        return TipoCita.CONSULTA;
    }
}
