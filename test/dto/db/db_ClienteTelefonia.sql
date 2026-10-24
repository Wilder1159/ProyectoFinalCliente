--Crear la base de datos--
CREATE DATABASE ClienteTelefoniaDB;


USE ClienteTelefoniaDB;


--Crear las seis tablas--
-- 1. CLIENTE--
CREATE TABLE dbo.Cliente (
    id_cliente BIGINT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(80) NOT NULL,
    apellido NVARCHAR(80) NOT NULL,
    telefono VARCHAR(16) NOT NULL,
    correo NVARCHAR(150) NULL,
    estado VARCHAR(10) NOT NULL
        CONSTRAINT DF_Cliente_Estado DEFAULT 'ACTIVO',
    fecha_registro DATETIME2(0) NOT NULL
        CONSTRAINT DF_Cliente_Fecha DEFAULT SYSDATETIME(),

    CONSTRAINT PK_Cliente PRIMARY KEY (id_cliente),
    CONSTRAINT UQ_Cliente_Telefono UNIQUE (telefono),
    CONSTRAINT CK_Cliente_Estado
        CHECK (estado IN ('ACTIVO', 'INACTIVO')),
    CONSTRAINT CK_Cliente_Nombre
        CHECK (LEN(TRIM(nombre)) > 0),
    CONSTRAINT CK_Cliente_Apellido
        CHECK (LEN(TRIM(apellido)) > 0)
);

CREATE UNIQUE INDEX UX_Cliente_Correo
ON dbo.Cliente(correo)
WHERE correo IS NOT NULL;

-- 2. CUENTA--
CREATE TABLE dbo.Cuenta (
    id_cuenta BIGINT IDENTITY(1,1) NOT NULL,
    id_cliente BIGINT NOT NULL,
    saldo DECIMAL(12,2) NOT NULL
        CONSTRAINT DF_Cuenta_Saldo DEFAULT 0.00,
    estado VARCHAR(12) NOT NULL
        CONSTRAINT DF_Cuenta_Estado DEFAULT 'ACTIVA',
    fecha_creacion DATETIME2(0) NOT NULL
        CONSTRAINT DF_Cuenta_Fecha DEFAULT SYSDATETIME(),

    CONSTRAINT PK_Cuenta PRIMARY KEY (id_cuenta),
    CONSTRAINT FK_Cuenta_Cliente FOREIGN KEY (id_cliente)
        REFERENCES dbo.Cliente(id_cliente),
    CONSTRAINT UQ_Cuenta_Cliente UNIQUE (id_cliente),
    CONSTRAINT CK_Cuenta_Saldo CHECK (saldo >= 0),
    CONSTRAINT CK_Cuenta_Estado
        CHECK (estado IN ('ACTIVA', 'BLOQUEADA'))
);

-- 3. TARIFA--
CREATE TABLE dbo.Tarifa (
    id_tarifa BIGINT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(80) NOT NULL,
    precio_minuto DECIMAL(10,4) NOT NULL,
    moneda CHAR(3) NOT NULL
        CONSTRAINT DF_Tarifa_Moneda DEFAULT 'PEN',
    segundos_minimos INT NOT NULL
        CONSTRAINT DF_Tarifa_Segundos DEFAULT 60,
    activa BIT NOT NULL
        CONSTRAINT DF_Tarifa_Activa DEFAULT 1,
    fecha_inicio DATETIME2(0) NOT NULL
        CONSTRAINT DF_Tarifa_Fecha DEFAULT SYSDATETIME(),

    CONSTRAINT PK_Tarifa PRIMARY KEY (id_tarifa),
    CONSTRAINT CK_Tarifa_Precio CHECK (precio_minuto >= 0),
    CONSTRAINT CK_Tarifa_Segundos CHECK (segundos_minimos > 0)
);

-- 4. RECARGA
CREATE TABLE dbo.Recarga (
    id_recarga BIGINT IDENTITY(1,1) NOT NULL,
    id_cuenta BIGINT NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    metodo VARCHAR(12) NOT NULL,
    estado VARCHAR(12) NOT NULL
        CONSTRAINT DF_Recarga_Estado DEFAULT 'PENDIENTE',
    referencia VARCHAR(120) NULL,
    fecha_creacion DATETIME2(0) NOT NULL
        CONSTRAINT DF_Recarga_Fecha DEFAULT SYSDATETIME(),
    fecha_confirmacion DATETIME2(0) NULL,

    CONSTRAINT PK_Recarga PRIMARY KEY (id_recarga),
    CONSTRAINT FK_Recarga_Cuenta FOREIGN KEY (id_cuenta)
        REFERENCES dbo.Cuenta(id_cuenta),
    CONSTRAINT CK_Recarga_Monto CHECK (monto > 0),
    CONSTRAINT CK_Recarga_Metodo CHECK (
        metodo IN ('TARJETA', 'EFECTIVO', 'YAPE', 'OTRO')
    ),
    CONSTRAINT CK_Recarga_Estado CHECK (
        estado IN ('PENDIENTE', 'CONFIRMADA',
                   'RECHAZADA', 'ANULADA')
    )
);

CREATE UNIQUE INDEX UX_Recarga_Referencia
ON dbo.Recarga(referencia)
WHERE referencia IS NOT NULL;

-- 5. LLAMADA
CREATE TABLE dbo.Llamada (
    id_llamada BIGINT IDENTITY(1,1) NOT NULL,
    id_cuenta BIGINT NOT NULL,
    id_tarifa BIGINT NOT NULL,
    destino VARCHAR(16) NOT NULL,
    proveedor VARCHAR(30) NOT NULL
        CONSTRAINT DF_Llamada_Proveedor DEFAULT 'TELNYX',
    call_control_id VARCHAR(150) NULL,
    call_session_id VARCHAR(150) NULL,
    estado VARCHAR(15) NOT NULL
        CONSTRAINT DF_Llamada_Estado DEFAULT 'SOLICITADA',
    fecha_solicitud DATETIME2(0) NOT NULL
        CONSTRAINT DF_Llamada_Fecha DEFAULT SYSDATETIME(),
    fecha_contestacion DATETIME2(0) NULL,
    fecha_finalizacion DATETIME2(0) NULL,
    duracion_facturable_segundos INT NOT NULL
        CONSTRAINT DF_Llamada_Duracion DEFAULT 0,
    costo DECIMAL(12,2) NOT NULL
        CONSTRAINT DF_Llamada_Costo DEFAULT 0.00,
    moneda CHAR(3) NOT NULL
        CONSTRAINT DF_Llamada_Moneda DEFAULT 'PEN',

    CONSTRAINT PK_Llamada PRIMARY KEY (id_llamada),
    CONSTRAINT FK_Llamada_Cuenta FOREIGN KEY (id_cuenta)
        REFERENCES dbo.Cuenta(id_cuenta),
    CONSTRAINT FK_Llamada_Tarifa FOREIGN KEY (id_tarifa)
        REFERENCES dbo.Tarifa(id_tarifa),
    CONSTRAINT CK_Llamada_Estado CHECK (
        estado IN ('SOLICITADA', 'TIMBRANDO',
                   'CONTESTADA', 'FINALIZADA',
                   'FALLIDA', 'CANCELADA')
    ),
    CONSTRAINT CK_Llamada_Duracion
        CHECK (duracion_facturable_segundos >= 0),
    CONSTRAINT CK_Llamada_Costo CHECK (costo >= 0)
);

CREATE UNIQUE INDEX UX_Llamada_Control
ON dbo.Llamada(call_control_id)
WHERE call_control_id IS NOT NULL;

-- 6. MOVIMIENTO DE SALDO--
CREATE TABLE dbo.MovimientoSaldo (
    id_movimiento BIGINT IDENTITY(1,1) NOT NULL,
    id_cuenta BIGINT NOT NULL,
    id_recarga BIGINT NULL,
    id_llamada BIGINT NULL,
    tipo VARCHAR(12) NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    saldo_anterior DECIMAL(12,2) NOT NULL,
    saldo_posterior DECIMAL(12,2) NOT NULL,
    descripcion NVARCHAR(255) NULL,
    fecha DATETIME2(0) NOT NULL
        CONSTRAINT DF_Movimiento_Fecha DEFAULT SYSDATETIME(),

    CONSTRAINT PK_Movimiento PRIMARY KEY (id_movimiento),
    CONSTRAINT FK_Movimiento_Cuenta FOREIGN KEY (id_cuenta)
        REFERENCES dbo.Cuenta(id_cuenta),
    CONSTRAINT FK_Movimiento_Recarga FOREIGN KEY (id_recarga)
        REFERENCES dbo.Recarga(id_recarga),
    CONSTRAINT FK_Movimiento_Llamada FOREIGN KEY (id_llamada)
        REFERENCES dbo.Llamada(id_llamada),
    CONSTRAINT CK_Movimiento_Tipo CHECK (
        tipo IN ('RECARGA', 'CONSUMO',
                 'REEMBOLSO', 'AJUSTE')
    ),
    CONSTRAINT CK_Movimiento_Monto CHECK (monto > 0),
    CONSTRAINT CK_Movimiento_Saldos CHECK (
        saldo_anterior >= 0 AND saldo_posterior >= 0
    ),
    CONSTRAINT CK_Movimiento_Origen CHECK (
        NOT (id_recarga IS NOT NULL AND id_llamada IS NOT NULL)
    )
);

--constrains--
CREATE UNIQUE INDEX UX_Movimiento_Recarga
ON dbo.MovimientoSaldo(id_recarga)
WHERE id_recarga IS NOT NULL;

CREATE UNIQUE INDEX UX_Movimiento_Llamada
ON dbo.MovimientoSaldo(id_llamada)
WHERE id_llamada IS NOT NULL;

--Crear índices de consulta
CREATE INDEX IX_Recarga_Cuenta_Fecha
ON dbo.Recarga(id_cuenta, fecha_creacion DESC);

CREATE INDEX IX_Llamada_Cuenta_Fecha
ON dbo.Llamada(id_cuenta, fecha_solicitud DESC);

CREATE INDEX IX_Llamada_Estado
ON dbo.Llamada(estado);

CREATE INDEX IX_Movimiento_Cuenta_Fecha
ON dbo.MovimientoSaldo(id_cuenta, fecha DESC);

--Insertar la tarifa inicial, para el test--
USE ClienteTelefoniaDB;

INSERT INTO dbo.Tarifa(nombre, precio_minuto, moneda, segundos_minimos)
VALUES (N'Tarifa de prueba', 0.200, 'PEN', 60);

SELECT * FROM dbo.Tarifa;

--Comprobar las tablas
USE ClienteTelefoniaDB;

SELECT
    TABLE_SCHEMA AS Esquema,
    TABLE_NAME AS Tabla
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_TYPE = 'BASE TABLE'
  AND TABLE_SCHEMA = 'dbo'
ORDER BY TABLE_NAME;

--verificarl el sql y conectarlo con java
USE ClienteTelefoniaDB;

SELECT
    @@SERVERNAME AS Servidor,
    DB_NAME() AS BaseDatos,
    SUSER_SNAME() AS UsuarioActual;

    --creacion y activacion de las cuentas con saldo inicial 0.0
USE ClienteTelefoniaDB;

SELECT
    name AS restriccion,
    definition AS regla,
    is_disabled AS deshabilitada
FROM sys.check_constraints
WHERE name = 'CK_Cuenta_Estado';

--2. Comprueba si se creó alguna cuenta
SELECT id_cuenta, id_cliente, saldo, estado
FROM dbo.Cuenta
ORDER BY id_cuenta;


SELECT
    c.id_cliente,
    c.nombre,
    c.apellido,
    c.telefono,
    cu.id_cuenta,
    cu.saldo,
    cu.estado AS estado_cuenta
FROM dbo.Cliente AS c
LEFT JOIN dbo.Cuenta AS cu
    ON cu.id_cliente = c.id_cliente
ORDER BY c.id_cliente;

--codigo correcto para la activacion y creacion de cuentas para clientes activos sin cuenta
USE ClienteTelefoniaDB;

SET XACT_ABORT ON;
BEGIN TRANSACTION;

BEGIN TRY
    INSERT INTO dbo.Cuenta (id_cliente, saldo, estado)
    SELECT
        c.id_cliente,
        0.00,
        'ACTIVA'
    FROM dbo.Cliente AS c
    WHERE c.estado = 'ACTIVO'
      AND NOT EXISTS (
          SELECT 1
          FROM dbo.Cuenta AS cu
          WHERE cu.id_cliente = c.id_cliente
      );

    COMMIT TRANSACTION;

    PRINT 'Cuentas creadas correctamente.';
END TRY
BEGIN CATCH
    IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION;

    SELECT
        ERROR_NUMBER() AS numero_error,
        ERROR_MESSAGE() AS detalle_error;
END CATCH;