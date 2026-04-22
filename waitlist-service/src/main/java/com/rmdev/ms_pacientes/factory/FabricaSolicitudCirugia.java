package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.dto.SolicitudCirugia;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.CitaCirugia;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

/**
 * Fábrica concreta para citas de tipo Cirugía.
 */
@Component
public class FabricaSolicitudCirugia implements FabricaSolicitudMedica {

    @Override
    public Cita crear(Object solicitud) {
        if (!(solicitud instanceof SolicitudCirugia dto)) {
            throw new IllegalArgumentException("Se esperaba un SolicitudCirugia");
        }
        return CitaCirugia.builder()
                .pacienteId(dto.pacienteId())
                .medicoId(dto.medicoId())
                .fechaProgramada(dto.fechaProgramada())
                .observaciones(dto.observaciones())
                .nombreProcedimiento(dto.nombreProcedimiento())
                .requiereAnestesiaGeneral(dto.requiereAnestesiaGeneral())
                .duracionEstimadaMinutos(dto.duracionEstimadaMinutos())
                .build();
    }

    @Override
    public TipoCita getTipo() {
        return TipoCita.CIRUGIA;
    }
}
