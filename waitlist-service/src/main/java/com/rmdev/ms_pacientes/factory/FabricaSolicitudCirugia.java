package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.dto.SolicitudCirugia;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.CitaCirugia;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudCirugia implements FabricaSolicitudMedica {

    @Override
    public Cita crear(Object solicitud) {
        if (!(solicitud instanceof SolicitudCirugia dto)) {
            throw new IllegalArgumentException("Se esperaba un SolicitudCirugia");
        }
        CitaCirugia cita = new CitaCirugia();
        cita.setPacienteId(dto.pacienteId());
        cita.setMedicoId(dto.medicoId());
        cita.setFechaProgramada(dto.fechaProgramada());
        cita.setObservaciones(dto.observaciones());
        cita.setNombreProcedimiento(dto.nombreProcedimiento());
        cita.setRequiereAnestesiaGeneral(dto.requiereAnestesiaGeneral());
        cita.setDuracionEstimadaMinutos(dto.duracionEstimadaMinutos());
        return cita;
    }

    @Override
    public TipoCita getTipo() {
        return TipoCita.CIRUGIA;
    }
}
