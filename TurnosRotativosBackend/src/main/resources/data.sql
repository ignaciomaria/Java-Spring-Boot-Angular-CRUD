-- Conceptos
INSERT INTO conceptos (id, hs_minimo, hs_maximo, laborable, nombre)
VALUES (1, 6, 8, true, 'Turno Normal');
INSERT INTO conceptos (id, hs_minimo, hs_maximo, laborable, nombre)
VALUES (2, 2, 6, true, 'Turno Extra');
INSERT INTO conceptos (id, hs_minimo, hs_maximo, laborable, nombre)
VALUES (3, null, null, false, 'Dia Libre');

-- Empleados de prueba
INSERT INTO empleados (nro_documento, nombre, apellido, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES (38745630, 'Sebastian', 'Fernandez', 'sebi@gmail.com', '1994-07-23', '2020-2-5', CURRENT_TIMESTAMP);

INSERT INTO empleados (nro_documento, nombre, apellido, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES (333448762, 'Francisco', 'Alvarez', 'fran@yahoo.com.ar', '1993-10-02', '2017-4-8', CURRENT_TIMESTAMP);

INSERT INTO empleados (nro_documento, nombre, apellido, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES (30636178, 'Rodrigo', 'Caceres', 'ro.caceres@hotmail.com', '2001-05-16', '2021-08-18', CURRENT_TIMESTAMP);