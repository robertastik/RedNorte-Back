package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.dto.SolicitudDiagnostico;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.CitaDiagnostico;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudDiagnostico implements FabricaSolicitudMedica {

    @Override
    public Cita crear(Object solicitud) {
        if (!(solicitud instanceof SolicitudDiagnostico dto)) {
            throw new IllegalArgumentException("Se esperaba un SolicitudDiagnostico");
        }
        CitaDiagnostico cita = new CitaDiagnostico();
        cita.setPacienteId(dto.pacienteId());
        cita.setMedicoId(dto.medicoId());
        cita.setFechaProgramada(dto.fechaProgramada());
        cita.setObservaciones(dto.observaciones());
        cita.setTipoExamen(dto.tipoExamen());
        cita.setRequiereAyuno(dto.requiereAyuno());
        return cita;
    }

    @Override
    public TipoCita getTipo() {
        return TipoCita.DIAGNOSTICO;
    }
}
