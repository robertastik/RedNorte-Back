package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.dto.SolicitudConsulta;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.CitaConsulta;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

/**
 * Fábrica concreta para citas de tipo Consulta.
 */
@Component
public class FabricaSolicitudConsulta implements FabricaSolicitudMedica {

    @Override
    public Cita crear(Object solicitud) {
        if (!(solicitud instanceof SolicitudConsulta dto)) {
            throw new IllegalArgumentException("Se esperaba un SolicitudConsulta");
        }
        return CitaConsulta.builder()
                .pacienteId(dto.pacienteId())
                .medicoId(dto.medicoId())
                .fechaProgramada(dto.fechaProgramada())
                .observaciones(dto.observaciones())
                .especialidad(dto.especialidad())
                .esSeguimiento(dto.esSeguimiento())
                .build();
    }

    @Override
    public TipoCita getTipo() {
        return TipoCita.CONSULTA;
    }
}
