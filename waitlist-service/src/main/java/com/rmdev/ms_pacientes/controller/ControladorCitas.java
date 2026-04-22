package com.rmdev.ms_pacientes.controller;

import com.rmdev.ms_pacientes.dto.RespuestaCita;
import com.rmdev.ms_pacientes.dto.SolicitudConsulta;
import com.rmdev.ms_pacientes.dto.SolicitudCirugia;
import com.rmdev.ms_pacientes.dto.SolicitudDiagnostico;
import com.rmdev.ms_pacientes.model.TipoCita;
import com.rmdev.ms_pacientes.service.ServicioCitas;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para la gestión de citas médicas.
 *
 * Endpoints expuestos:
 *   POST   /api/v1/citas/consulta        → Crear cita de consulta
 *   GET    /api/v1/citas/{id}            → Obtener cita por ID
 *   GET    /api/v1/citas/paciente/{id}   → Listar citas de un paciente
 *   PATCH  /api/v1/citas/{id}/cancelar   → Cancelar una cita
 */
@RestController
@RequestMapping("/api/v1/citas")
@RequiredArgsConstructor
public class ControladorCitas {

    private final ServicioCitas servicioCitas;

    /**
     * Endpoint 1: Crear una cita de tipo Consulta.
     * POST /api/v1/citas/consulta
     */
    @PostMapping("/consulta")
    public ResponseEntity<RespuestaCita> crearConsulta(@Valid @RequestBody SolicitudConsulta solicitud) {
        RespuestaCita respuesta = servicioCitas.crearCita(TipoCita.CONSULTA, solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * Endpoint 2: Crear una cita de tipo Cirugía.
     * POST /api/v1/citas/cirugia
     */
    @PostMapping("/cirugia")
    public ResponseEntity<RespuestaCita> crearCirugia(@Valid @RequestBody SolicitudCirugia solicitud) {
        RespuestaCita respuesta = servicioCitas.crearCita(TipoCita.CIRUGIA, solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * Endpoint 3: Crear una cita de tipo Diagnóstico.
     * POST /api/v1/citas/diagnostico
     */
    @PostMapping("/diagnostico")
    public ResponseEntity<RespuestaCita> crearDiagnostico(@Valid @RequestBody SolicitudDiagnostico solicitud) {
        RespuestaCita respuesta = servicioCitas.crearCita(TipoCita.DIAGNOSTICO, solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * Endpoint 4: Obtener una cita por ID.
     * GET /api/v1/citas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCita> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCitas.obtenerPorId(id));
    }

    /**
     * Endpoint 5: Listar todas las citas de un paciente.
     * GET /api/v1/citas/paciente/{pacienteId}
     */
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<RespuestaCita>> listarPorPaciente(@PathVariable UUID pacienteId) {
        return ResponseEntity.ok(servicioCitas.listarPorPaciente(pacienteId));
    }

    /**
     * Endpoint 6: Cancelar una cita.
     * PATCH /api/v1/citas/{id}/cancelar
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<RespuestaCita> cancelarCita(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCitas.cancelarCita(id));
    }
}
