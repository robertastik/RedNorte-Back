package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Appointment;
import com.rmdev.ms_pacientes.model.AppointmentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Registro central de fábricas de solicitudes médicas.
 * Spring inyecta automáticamente todas las implementaciones de MedicalRequestFactory.
 * El servicio usa este registro para obtener la fábrica correcta según el tipo de cita.
 */
@Component
public class MedicalRequestFactoryRegistry {

    private final Map<AppointmentType, MedicalRequestFactory> factories;

    public MedicalRequestFactoryRegistry(List<MedicalRequestFactory> factoryList) {
        this.factories = factoryList.stream()
                .collect(Collectors.toMap(MedicalRequestFactory::getType, Function.identity()));
    }

    /**
     * Obtiene la fábrica correspondiente al tipo de cita solicitado.
     *
     * @param type Tipo de cita médica.
     * @return MedicalRequestFactory concreta.
     * @throws IllegalArgumentException si no existe fábrica para el tipo dado.
     */
    public Appointment create(AppointmentType type, Object request) {
        MedicalRequestFactory factory = factories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("No existe fábrica registrada para el tipo: " + type);
        }
        return factory.create(request);
    }
}
