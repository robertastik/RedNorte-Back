package com.rmdev.ms_pacientes.repository;

import com.rmdev.ms_pacientes.model.Appointment;
import com.rmdev.ms_pacientes.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad Appointment (patrón Repository).
 * Spring Data genera automáticamente las implementaciones en tiempo de ejecución.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    /** Obtiene todas las citas de un paciente específico. */
    List<Appointment> findByPatientId(UUID patientId);

    /** Obtiene todas las citas de un paciente filtradas por estado. */
    List<Appointment> findByPatientIdAndStatus(UUID patientId, AppointmentStatus status);

    /** Obtiene todas las citas de un doctor en un rango de tiempo (para detección de conflictos). */
    @Query("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId " +
           "AND a.scheduledAt BETWEEN :from AND :to " +
           "AND a.status NOT IN ('CANCELLED', 'NO_SHOW')")
    List<Appointment> findDoctorScheduleInRange(
            @Param("doctorId") UUID doctorId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    /** Cuenta citas pendientes o confirmadas por doctor (carga de trabajo). */
    long countByDoctorIdAndStatusIn(UUID doctorId, List<AppointmentStatus> statuses);
}
