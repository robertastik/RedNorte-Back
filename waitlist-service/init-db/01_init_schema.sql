-- Script de inicialización de la base de datos rednorte_waitlist
-- Se ejecuta automáticamente la primera vez que se levanta el contenedor de Postgres

-- Tabla principal de citas (SINGLE_TABLE - todas las subclases en una sola tabla)
CREATE TABLE IF NOT EXISTS citas (
    -- Discriminador de herencia
    tipo_cita           VARCHAR(20)     NOT NULL,

    -- Campos base
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    paciente_id         UUID            NOT NULL,
    medico_id           UUID            NOT NULL,
    fecha_programada    TIMESTAMP       NOT NULL,
    estado              VARCHAR(20)     NOT NULL DEFAULT 'PENDIENTE',
    observaciones       VARCHAR(500),
    creado_en           TIMESTAMP       NOT NULL DEFAULT NOW(),
    actualizado_en      TIMESTAMP,

    -- Campos exclusivos de CitaConsulta (tipo_cita = 'CONSULTA')
    especialidad        VARCHAR(100),
    es_seguimiento      BOOLEAN,

    -- Campos exclusivos de CitaCirugia (tipo_cita = 'CIRUGIA')
    nombre_procedimiento        VARCHAR(200),
    requiere_anestesia_general  BOOLEAN,
    duracion_estimada_minutos   INTEGER,

    -- Campos exclusivos de CitaDiagnostico (tipo_cita = 'DIAGNOSTICO')
    tipo_examen         VARCHAR(150),
    requiere_ayuno      BOOLEAN
);

-- Índices para consultas frecuentes
CREATE INDEX IF NOT EXISTS idx_citas_paciente_id   ON citas(paciente_id);
CREATE INDEX IF NOT EXISTS idx_citas_medico_id     ON citas(medico_id);
CREATE INDEX IF NOT EXISTS idx_citas_estado        ON citas(estado);
CREATE INDEX IF NOT EXISTS idx_citas_fecha         ON citas(fecha_programada);
