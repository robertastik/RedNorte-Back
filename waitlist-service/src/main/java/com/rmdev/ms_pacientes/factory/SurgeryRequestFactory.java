package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Appointment;
import com.rmdev.ms_pacientes.model.AppointmentType;
import com.rmdev.ms_pacientes.model.SurgeryAppointment;
import com.rmdev.ms_pacientes.dto.CreateSurgeryRequest;
import org.springframework.stereotype.Component;

/**
 * Fábrica concreta para citas de tipo Cirugía.
 */
@Component
public class SurgeryRequestFactory implements MedicalRequestFactory {

    @Override
    public Appointment create(Object request) {
        if (!(request instanceof CreateSurgeryRequest dto)) {
            throw new IllegalArgumentException("Se esperaba un CreateSurgeryRequest");
        }
        return SurgeryAppointment.builder()
                .patientId(dto.patientId())
                .doctorId(dto.doctorId())
                .scheduledAt(dto.scheduledAt())
                .notes(dto.notes())
                .procedureName(dto.procedureName())
                .requiresGeneralAnesthesia(dto.requiresGeneralAnesthesia())
                .estimatedDurationMinutes(dto.estimatedDurationMinutes())
                .build();
    }

    @Override
    public AppointmentType getType() {
        return AppointmentType.SURGERY;
    }
}
