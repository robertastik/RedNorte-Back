package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Appointment;
import com.rmdev.ms_pacientes.model.AppointmentType;

/**
 * Interfaz del patrón Factory Method para la creación de solicitudes médicas.
 * Cada tipo concreto implementa su propia lógica de instanciación y validación.
 */
public interface MedicalRequestFactory {

    /**
     * Crea una instancia de Appointment del tipo correspondiente.
     *
     * @param request DTO con los datos de la solicitud.
     * @return Appointment instanciado y listo para persistir.
     */
    Appointment create(Object request);

    /**
     * Retorna el tipo de cita que esta fábrica sabe construir.
     */
    AppointmentType getType();
}
