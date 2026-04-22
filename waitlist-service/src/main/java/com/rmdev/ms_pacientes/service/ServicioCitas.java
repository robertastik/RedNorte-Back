package com.rmdev.ms_pacientes.service;

import com.rmdev.ms_pacientes.dto.RespuestaCita;
import com.rmdev.ms_pacientes.factory.RegistroFabricasCitas;
import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.EstadoCita;
import com.rmdev.ms_pacientes.model.TipoCita;
import com.rmdev.ms_pacientes.repository.RepositorioCita;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Servicio de lógica de negocio para la gestión de citas médicas.
 * Orquesta el Factory Method, el repositorio y (en futuras iteraciones) el publicador Kafka.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ServicioCitas {

    private final RepositorioCita repositorioCita;
    private final RegistroFabricasCitas registroFabricas;

    /**
     * Crea una nueva cita médica usando el Factory Method correspondiente al tipo.
     *
     * @param tipo      Tipo de cita (CONSULTA, CIRUGIA, DIAGNOSTICO).
     * @param solicitud DTO con los datos de la solicitud.
     * @return RespuestaCita con los datos de la cita creada.
     */
    @Transactional
    public RespuestaCita crearCita(TipoCita tipo, Object solicitud) {
        log.info("Creando cita de tipo: {}", tipo);
        Cita cita = registroFabricas.crear(tipo, solicitud);
        Cita citaGuardada = repositorioCita.save(cita);
        log.info("Cita creada con ID: {}", citaGuardada.getId());
        return mapearRespuesta(citaGuardada, tipo);
    }

    /**
     * Obtiene una cita por su ID.
     *
     * @param id UUID de la cita.
     * @return RespuestaCita con los datos.
     * @throws NoSuchElementException si no existe la cita.
     */
    @Transactional(readOnly = true)
    public RespuestaCita obtenerPorId(UUID id) {
        Cita cita = repositorioCita.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada con ID: " + id));
        TipoCita tipo = resolverTipo(cita);
        return mapearRespuesta(cita, tipo);
    }

    /**
     * Lista todas las citas de un paciente.
     *
     * @param pacienteId UUID del paciente.
     * @return Lista de RespuestaCita.
     */
    @Transactional(readOnly = true)
    public List<RespuestaCita> listarPorPaciente(UUID pacienteId) {
        return repositorioCita.findByPacienteId(pacienteId).stream()
                .map(c -> mapearRespuesta(c, resolverTipo(c)))
                .toList();
    }

    /**
     * Cancela una cita existente.
     *
     * @param id UUID de la cita a cancelar.
     * @return RespuestaCita con estado actualizado.
     */
    @Transactional
    public RespuestaCita cancelarCita(UUID id) {
        Cita cita = repositorioCita.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada con ID: " + id));
        if (cita.getEstado() == EstadoCita.CANCELADA) {
            throw new IllegalStateException("La cita ya se encuentra cancelada.");
        }
        cita.setEstado(EstadoCita.CANCELADA);
        Cita citaActualizada = repositorioCita.save(cita);
        log.info("Cita {} cancelada.", id);
        return mapearRespuesta(citaActualizada, resolverTipo(citaActualizada));
    }

    // --- Helpers privados ---

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
        return switch (cita) {
            case com.rmdev.ms_pacientes.model.CitaConsulta ignored   -> TipoCita.CONSULTA;
            case com.rmdev.ms_pacientes.model.CitaCirugia ignored    -> TipoCita.CIRUGIA;
            case com.rmdev.ms_pacientes.model.CitaDiagnostico ignored -> TipoCita.DIAGNOSTICO;
            default -> throw new IllegalStateException("Tipo de cita desconocido: " + cita.getClass());
        };
    }
}
