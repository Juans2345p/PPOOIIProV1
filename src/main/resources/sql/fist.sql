-- 1. Tabla Vehículos
CREATE TABLE vehiculos (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('auto', 'moto')),
    placa CHAR(6) NOT NULL UNIQUE,
    servicio VARCHAR(10) NOT NULL CHECK (servicio IN ('publico', 'privado')),
    combustible VARCHAR(15) NOT NULL CHECK (combustible IN ('gasolina', 'gas', 'diesel')),
    capacidad SMALLINT NOT NULL,
    color VARCHAR(7),
    modelo INTEGER NOT NULL,
    marca VARCHAR(50) NOT NULL,
    linea VARCHAR(50) NOT NULL
);

-- 2. Tabla Documentos (Maestra)
CREATE TABLE documentos (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    aplica_a VARCHAR(2) NOT NULL CHECK (aplica IN ('A', 'M', 'AM')),
    obligatoriedad VARCHAR(2) NOT NULL CHECK (obligatoriedad IN ('RA', 'RM', 'RR')),
    descripcion TEXT
);

-- 3. Tabla Relación (Vehículo <-> Documentos)
CREATE TABLE vehiculo_documentos (
    id BIGSERIAL PRIMARY KEY,
    vehiculo_id BIGINT NOT NULL REFERENCES vehiculos(id),
    documento_id BIGINT NOT NULL REFERENCES documentos(id),
    fecha_expedicion DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('Habilitado', 'Vencido', 'En Verificación'))
);