package com.rmdev.ms_pacientes.factory;

import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.TipoCita;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RegistroFabricasCitas {

    private final Map<TipoCita, FabricaSolicitudMedica> fabricas;

    public RegistroFabricasCitas(List<FabricaSolicitudMedica> listaFabricas) {
        this.fabricas = listaFabricas.stream()
                .collect(Collectors.toMap(FabricaSolicitudMedica::getTipo, Function.identity()));
    }

    public Cita crear(TipoCita tipo, Object solicitud) {
        FabricaSolicitudMedica fabrica = fabricas.get(tipo);
        if (fabrica == null) {
            throw new IllegalArgumentException("No existe fábrica registrada para el tipo: " + tipo);
        }
        return fabrica.crear(solicitud);
    }
}
