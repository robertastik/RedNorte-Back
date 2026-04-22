package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Appointment;
import com.rmdev.ms_pacientes.model.AppointmentType;
import com.rmdev.ms_pacientes.model.DiagnosisAppointment;
import com.rmdev.ms_pacientes.dto.CreateDiagnosisRequest;
import org.springframework.stereotype.Component;

/**
 * Fábrica concreta para citas de tipo Diagnóstico.
 */
@Component
public class DiagnosisRequestFactory implements MedicalRequestFactory {

    @Override
    public Appointment create(Object request) {
        if (!(request instanceof CreateDiagnosisRequest dto)) {
            throw new IllegalArgumentException("Se esperaba un CreateDiagnosisRequest");
        }
        return DiagnosisAppointment.builder()
                .patientId(dto.patientId())
                .doctorId(dto.doctorId())
                .scheduledAt(dto.scheduledAt())
                .notes(dto.notes())
                .examType(dto.examType())
                .requiresFasting(dto.requiresFasting())
                .build();
    }

    @Override
    public AppointmentType getType() {
        return AppointmentType.DIAGNOSIS;
    }
}
