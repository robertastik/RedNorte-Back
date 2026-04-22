package com.rmdev.ms_pacientes.controller;

import com.rmdev.ms_pacientes.dto.RespuestaCita;
import com.rmdev.ms_pacientes.dto.SolicitudConsulta;
import com.rmdev.ms_pacientes.dto.SolicitudCirugia;
import com.rmdev.ms_pacientes.dto.SolicitudDiagnostico;
import com.rmdev.ms_pacientes.model.TipoCita;
import com.rmdev.ms_pacientes.service.ServicioCitas;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/citas")
public class ControladorCitas {

    private final ServicioCitas servicioCitas;

    public ControladorCitas(ServicioCitas servicioCitas) {
        this.servicioCitas = servicioCitas;
    }

    /** POST /api/v1/citas/consulta */
    @PostMapping("/consulta")
    public ResponseEntity<RespuestaCita> crearConsulta(@Valid @RequestBody SolicitudConsulta solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCitas.crearCita(TipoCita.CONSULTA, solicitud));
    }

    /** POST /api/v1/citas/cirugia */
    @PostMapping("/cirugia")
    public ResponseEntity<RespuestaCita> crearCirugia(@Valid @RequestBody SolicitudCirugia solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCitas.crearCita(TipoCita.CIRUGIA, solicitud));
    }

    /** POST /api/v1/citas/diagnostico */
    @PostMapping("/diagnostico")
    public ResponseEntity<RespuestaCita> crearDiagnostico(@Valid @RequestBody SolicitudDiagnostico solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCitas.crearCita(TipoCita.DIAGNOSTICO, solicitud));
    }

    /** GET /api/v1/citas/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCita> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCitas.obtenerPorId(id));
    }

    /** GET /api/v1/citas/paciente/{pacienteId} */
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<RespuestaCita>> listarPorPaciente(@PathVariable UUID pacienteId) {
        return ResponseEntity.ok(servicioCitas.listarPorPaciente(pacienteId));
    }

    /** PATCH /api/v1/citas/{id}/cancelar */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<RespuestaCita> cancelarCita(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCitas.cancelarCita(id));
    }
}
