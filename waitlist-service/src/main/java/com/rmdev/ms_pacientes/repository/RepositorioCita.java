package com.rmdev.ms_pacientes.repository;

import com.rmdev.ms_pacientes.model.Cita;
import com.rmdev.ms_pacientes.model.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad Cita (patrón Repository).
 * Spring Data genera las implementaciones automáticamente en tiempo de ejecución.
 */
@Repository
public interface RepositorioCita extends JpaRepository<Cita, UUID> {

    /** Obtiene todas las citas de un paciente específico. */
    List<Cita> findByPacienteId(UUID pacienteId);

    /** Obtiene todas las citas de un paciente filtradas por estado. */
    List<Cita> findByPacienteIdAndEstado(UUID pacienteId, EstadoCita estado);

    /** Obtiene las citas de un médico en un rango de tiempo (detección de conflictos). */
    @Query("SELECT c FROM Cita c WHERE c.medicoId = :medicoId " +
           "AND c.fechaProgramada BETWEEN :desde AND :hasta " +
           "AND c.estado NOT IN ('CANCELADA', 'NO_ASISTIO')")
    List<Cita> findAgendaMedicoEnRango(
            @Param("medicoId") UUID medicoId,
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta
    );

    /** Cuenta citas activas por médico (métrica de carga de trabajo). */
    long countByMedicoIdAndEstadoIn(UUID medicoId, List<EstadoCita> estados);
}
