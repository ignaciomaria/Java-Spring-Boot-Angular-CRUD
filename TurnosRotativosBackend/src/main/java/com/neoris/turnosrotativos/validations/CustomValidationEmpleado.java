package com.neoris.turnosrotativos.validations;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import com.neoris.turnosrotativos.dto.EmpleadoDto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.BadRequestException;
import com.neoris.turnosrotativos.exceptions.ConflictException;

public class CustomValidationEmpleado {
	
	/*
	 * Validaciones para registrar o actualizar empleado
	 */
	public static void validacionesGenerales(EmpleadoDto empleadoDto, Optional<Empleado> traerEmpleadoPorDocumento
										, Optional<Empleado> traerEmpleadoPorEmail) {
		
		if(!validarDocumento(empleadoDto.getNroDocumento())) { //Verifica nro documento
			throw new BadRequestException("El nro de documento ingresado no es valido.");
		}else if(traerEmpleadoPorDocumento.isPresent()) { //Existencia del empleado por documento
			throw new ConflictException("Ya existe un empleado con el documento ingresado.");
		}else if(traerEmpleadoPorEmail.isPresent()) { //Existencia del empleado por email
			throw new ConflictException("Ya existe un empleado con el email ingresado.");
		}else if(empleadoDto.getFechaNacimiento().isAfter(LocalDate.now())) { //Fecha de nacimiento posterior a la presente
			throw new BadRequestException("La fecha de nacimiento no puede ser posterior al día de la fecha.");
		}else if(!empleadoMayorEdad(empleadoDto)) { //Empleado mayor de edad
			throw new BadRequestException("La edad del empleado no puede ser menor a 18 años.");
		}else if(empleadoDto.getFechaIngreso().isAfter(LocalDate.now())) { ////Fecha de ingreso posterior a la presente
			throw new BadRequestException("La fecha de ingreso no puede ser posterior al día de la fecha.");
		}
	}

	
	/*
	 * Verifica si el empleado es mayor de edad
	 */
	public static boolean empleadoMayorEdad(EmpleadoDto empleadoDto) {
		boolean mayor = true;
		LocalDate fechaNacimiento = empleadoDto.getFechaNacimiento();
		LocalDate fechaActual = LocalDate.now();
		if(Period.between(fechaNacimiento, fechaActual).getYears()<18) {
			mayor=false;
		}
		return mayor;
	}
	
	/*
	 * Verifica que el nro de documento no este vacio 
	 * y que contenga 8 digitos
	 */
	public static boolean validarDocumento(Integer documento) {
		boolean valido = true;
		String doc = documento.toString();
		if(doc.isEmpty() || doc.length()!=8) {
			valido = false;
		}
		return valido;
	}
}
