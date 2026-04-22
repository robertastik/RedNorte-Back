package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Registro central de fábricas de solicitudes médicas.
 * Spring inyecta automáticamente todas las implementaciones de FabricaSolicitudMedica.
 * El servicio usa este registro para obtener la fábrica correcta según el tipo de cita.
 */
@Component
public class RegistroFabricasCitas {

    private final Map<TipoCita, FabricaSolicitudMedica> fabricas;

    public RegistroFabricasCitas(List<FabricaSolicitudMedica> listaFabricas) {
        this.fabricas = listaFabricas.stream()
                .collect(Collectors.toMap(FabricaSolicitudMedica::getTipo, Function.identity()));
    }

    /**
     * Crea una Cita delegando en la fábrica correspondiente al tipo indicado.
     *
     * @param tipo     Tipo de cita médica.
     * @param solicitud DTO con los datos.
     * @return Cita instanciada.
     * @throws IllegalArgumentException si no existe fábrica para el tipo dado.
     */
    public Cita crear(TipoCita tipo, Object solicitud) {
        FabricaSolicitudMedica fabrica = fabricas.get(tipo);
        if (fabrica == null) {
            throw new IllegalArgumentException("No existe fábrica registrada para el tipo: " + tipo);
        }
        return fabrica.crear(solicitud);
    }
}
