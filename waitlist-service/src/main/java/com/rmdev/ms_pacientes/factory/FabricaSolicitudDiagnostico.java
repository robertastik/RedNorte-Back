package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.dto.SolicitudDiagnostico;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.CitaDiagnostico;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

/**
 * Fábrica concreta para citas de tipo Diagnóstico.
 */
@Component
public class FabricaSolicitudDiagnostico implements FabricaSolicitudMedica {

    @Override
    public Cita crear(Object solicitud) {
        if (!(solicitud instanceof SolicitudDiagnostico dto)) {
            throw new IllegalArgumentException("Se esperaba un SolicitudDiagnostico");
        }
        return CitaDiagnostico.builder()
                .pacienteId(dto.pacienteId())
                .medicoId(dto.medicoId())
                .fechaProgramada(dto.fechaProgramada())
                .observaciones(dto.observaciones())
                .tipoExamen(dto.tipoExamen())
                .requiereAyuno(dto.requiereAyuno())
                .build();
    }

    @Override
    public TipoCita getTipo() {
        return TipoCita.DIAGNOSTICO;
    }
}
