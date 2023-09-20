# Turnos Rotativos API

## Instructivo:

La aplicacion fue realizada con spring boot, utilizando Maven y Java 11,
siguiendo la arquitectura MVC.
Para la base de datos se utilizo H2.

## Funciones:

La aplicacion Turnos Rotativos lleva a cabo funciones como:
- [ ] Registro, actualizacion y eliminacion de empleados
- [ ] Resgistro de jornadas para dichos empleados
- [ ] Obtencion de datos tanto de los empleados como de sus jornadas laborales

Todas estas funciones se realizan por medio de URLs que envian las requests 
que el usuario. Ejemplo: /empleado, /jornada etc.

## Nota:
Si bien toda la logica quedo en la capa Service, se creo un paquete con clases 
que contienen validaciones para los servicios de Empleado y Jornada.
De esa manera el codigo tiene un mejor mantenimiento.

----------------------------------------------------------------------------------------------------