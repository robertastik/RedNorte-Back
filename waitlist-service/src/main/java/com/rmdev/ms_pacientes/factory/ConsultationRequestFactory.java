package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Appointment;
import com.rmdev.ms_pacientes.model.AppointmentType;
import com.rmdev.ms_pacientes.model.ConsultationAppointment;
import com.rmdev.ms_pacientes.dto.CreateConsultationRequest;
import org.springframework.stereotype.Component;

/**
 * Fábrica concreta para citas de tipo Consulta.
 */
@Component
public class ConsultationRequestFactory implements MedicalRequestFactory {

    @Override
    public Appointment create(Object request) {
        if (!(request instanceof CreateConsultationRequest dto)) {
            throw new IllegalArgumentException("Se esperaba un CreateConsultationRequest");
        }
        return ConsultationAppointment.builder()
                .patientId(dto.patientId())
                .doctorId(dto.doctorId())
                .scheduledAt(dto.scheduledAt())
                .notes(dto.notes())
                .specialty(dto.specialty())
                .isFollowUp(dto.isFollowUp())
                .build();
    }

    @Override
    public AppointmentType getType() {
        return AppointmentType.CONSULTATION;
    }
}
