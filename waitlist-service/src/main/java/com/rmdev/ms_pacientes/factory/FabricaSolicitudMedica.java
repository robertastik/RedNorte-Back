package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.TipoCita;

/**
 * Interfaz del patrón Factory Method para la creación de solicitudes médicas.
 */
public interface FabricaSolicitudMedica {

    /**
     * Crea una instancia de Cita del tipo correspondiente.
     *
     * @param solicitud DTO con los datos de la solicitud.
     * @return Cita instanciada y lista para persistir.
     */
    Cita crear(Object solicitud);

    /**
     * Retorna el tipo de cita que esta fábrica sabe construir.
     */
    TipoCita getTipo();
}
