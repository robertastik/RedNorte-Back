package com.rmdev.ms_pacientes.service;

import com.rmdev.ms_pacientes.dto.RespuestaCita;
import com.rmdev.ms_pacientes.factory.RegistroFabricasCitas;
import com.rmdev.ms_pacientes.model.*;
import com.rmdev.ms_pacientes.repository.RepositorioCita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ServicioCitas {

    private static final Logger log = Logger.getLogger(ServicioCitas.class.getName());

    private final RepositorioCita repositorioCita;
    private final RegistroFabricasCitas registroFabricas;

    public ServicioCitas(RepositorioCita repositorioCita, RegistroFabricasCitas registroFabricas) {
        this.repositorioCita = repositorioCita;
        this.registroFabricas = registroFabricas;
    }

    @Transactional
    public RespuestaCita crearCita(TipoCita tipo, Object solicitud) {
        log.info("Creando cita de tipo: " + tipo);
        Cita cita = registroFabricas.crear(tipo, solicitud);
        Cita citaGuardada = repositorioCita.save(cita);
        log.info("Cita creada con ID: " + citaGuardada.getId());
        return mapearRespuesta(citaGuardada, tipo);
    }

    @Transactional(readOnly = true)
    public RespuestaCita obtenerPorId(UUID id) {
        Cita cita = repositorioCita.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada con ID: " + id));
        return mapearRespuesta(cita, resolverTipo(cita));
    }

    @Transactional(readOnly = true)
    public List<RespuestaCita> listarPorPaciente(UUID pacienteId) {
        return repositorioCita.findByPacienteId(pacienteId).stream()
                .map(c -> mapearRespuesta(c, resolverTipo(c)))
                .toList();
    }

    @Transactional
    public RespuestaCita cancelarCita(UUID id) {
        Cita cita = repositorioCita.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada con ID: " + id));
        if (cita.getEstado() == EstadoCita.CANCELADA) {
            throw new IllegalStateException("La cita ya se encuentra cancelada.");
        }
        cita.setEstado(EstadoCita.CANCELADA);
        Cita citaActualizada = repositorioCita.save(cita);
        log.info("Cita " + id + " cancelada.");
        return mapearRespuesta(citaActualizada, resolverTipo(citaActualizada));
    }

    private RespuestaCita mapearRespuesta(Cita cita, TipoCita tipo) {
        return new RespuestaCita(
                cita.getId(),
                cita.getPacienteId(),
                cita.getMedicoId(),
                tipo,
                cita.getEstado(),
                cita.getFechaProgramada(),
                cita.getObservaciones(),
                cita.getCreadoEn()
        );
    }

    private TipoCita resolverTipo(Cita cita) {
        if (cita instanceof CitaConsulta)    return TipoCita.CONSULTA;
        if (cita instanceof CitaCirugia)     return TipoCita.CIRUGIA;
        if (cita instanceof CitaDiagnostico) return TipoCita.DIAGNOSTICO;
        throw new IllegalStateException("Tipo de cita desconocido: " + cita.getClass());
    }
}
