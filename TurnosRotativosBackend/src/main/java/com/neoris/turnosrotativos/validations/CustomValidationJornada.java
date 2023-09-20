package com.neoris.turnosrotativos.validations;

import java.util.List;
import java.util.Optional;

import com.neoris.turnosrotativos.dto.JornadaDto;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.BadRequestException;
import com.neoris.turnosrotativos.exceptions.NotFoundException;

/**
 * Validaciones personalizadas para el servicio Jornada
 */
public class CustomValidationJornada {
	
	/*
	 * Verifica si existe el empleado y el concepto ingresado
	 */
	public static void validarEmpleadoConceptoExist(Optional<Empleado> empleado, Optional<Concepto> concepto) {
		if(!empleado.isPresent()) {
			throw new NotFoundException("No existe el empleado ingresado.");
		} 
		if(!concepto.isPresent()) {
			throw new NotFoundException("No existe el concepto ingresado.");
		}
	}
	
	/*
	 * Verifica si la jornada requiere el ingreso
	 * de horas trabajadas o no
	 */
	public static void validarHsTrabajadasConceptos(JornadaDto jornadaDto, Optional<Concepto> concepto) {
		if(concepto.get().getLaborable() && jornadaDto.getHorasTrabajadas()==null) {
			throw new BadRequestException("‘horasTrabajadas’ es obligatorio para el concepto ingresado.");
		}else if(!concepto.get().getLaborable() && jornadaDto.getHorasTrabajadas()!=null) {
			throw new BadRequestException("El concepto ingresado no requiere el ingreso de ‘hsTrabajadas’.");
		}
	}
	
	/*
	 * Verifica si las horas trabajadas por el empleado
	 * estan dentro del rango segun el concepto de la jornada
	 */
	public static void validarEmpleadoHsJornada(JornadaDto jornadaDto, Optional<Empleado> empleado, Optional<Concepto> concepto) {
		if(concepto.get().getLaborable()) {
			if(jornadaDto.getHorasTrabajadas()<concepto.get().getHsMinimo() || jornadaDto.getHorasTrabajadas()>concepto.get().getHsMaximo()) {
				throw new BadRequestException("El rango de horas que se puede cargar para este concepto es de "
												+concepto.get().getHsMinimo()+" - "+concepto.get().getHsMaximo());
			}
		}
	}
	
	/*
	 * Verifica que un empleado con dia libre
	 * no pueda ingresar una nueva jornada en la misma fecha
	 */
	public static void validarEmpleadoDiaLibre(Optional<Empleado> empleado, Optional<Jornada> jornada) {
		if(jornada.isPresent() && jornada.get().getConcept().getId()==3) {
			if(jornada.get().getEmpleado().getId()==empleado.get().getId()) {
				throw new BadRequestException("El empleado ingresado cuenta con un día libre en esa fecha.");
			}
		}
	}
	
	/*
	 * Verifica que un empleado no tenga dos conceptos iguales
	 * en la misma jornada
	 */
	public static void validarEmpleadoMismoConcepto(Optional<Empleado> empleado, Optional<Concepto> concepto, Optional<Jornada> jornada) {
		if(jornada.isPresent() && jornada.get().getEmpleado().getId()==empleado.get().getId()) {
			if(jornada.get().getConcept().getId()==concepto.get().getId()) {
				throw new BadRequestException("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.");
			}
		}
	}
	
	/*
	 * Verifica si la suma de un turno normal y uno extra en la 
	 * misma jornada, no supere las 12 horas
	 */
	public static void validarTurnoNormalMasExtra(JornadaDto jornadaDto, Optional<Jornada> jornada) {
		if(jornada.isPresent() && jornada.get().getConcept().getId()==1) {
			if(jornadaDto.getIdConcepto()==2 && !(jornadaDto.getHorasTrabajadas()+jornada.get().getHorasTrabajadas()<=12)) {
				throw new BadRequestException("El empleado no puede cargar más de 12 horas trabajadas en un día.");
			}
		}
	}
	
	/*
	 * Verificar que no se pueda cargar un dia libre
	 * en una jornada con un turno ya establecido
	 */
	public static void validarDiaLibreDeMas(JornadaDto jornadaDto, Optional<Jornada> jornada) {
		if(jornada.isPresent() && jornadaDto.getIdConcepto()==3) {
			throw new BadRequestException("El empleado no puede cargar Dia Libre si cargo un turno previamente para la fecha ingresada.");
		}
	}
	
	/*
	 * Verificar que un empleado no pueda cargar mas
	 * de 48 hs semanales
	 */
	public static void validarHsSemanales(JornadaDto jornadaDto, List<Jornada> jornadas) {
		int cantidadHsSemanales = 0;
		/*
		for(Jornada j: jornadas) {
			if(j.getHorasTrabajadas()!=null) {
				cantidadHsSemanales += j.getHorasTrabajadas();
			}
		}*/
		cantidadHsSemanales = jornadas.stream().filter((j)->j.getHorasTrabajadas()!=null).mapToInt(v->v.getHorasTrabajadas()).sum();
		if(jornadaDto.getHorasTrabajadas()!=null) {
			cantidadHsSemanales += jornadaDto.getHorasTrabajadas();
		}
		if(cantidadHsSemanales>48) {
			throw new BadRequestException("El empleado ingresado supera las 48 horas semanales.");
		}
	}
	
	/*
	 * Verifica si el empleado no se pasa de los 3 turnos extra
	 * permititos por semana
	 */
	public static void validarTurnosExtraSemanal(JornadaDto jornadaDto, List<Jornada> jornadas) {
		int cantTurnosExtra = 0;
		/*for(Jornada j: jornadas){
			if(j.getIdConcepto()==2) {
				cantTurnosExtra++;
			}
		}*/
		cantTurnosExtra = (int) jornadas.stream().filter((j)->j.getConcept().getId()==2).count();
		if(jornadaDto.getIdConcepto()==2) {
			cantTurnosExtra++;
		}
		if(cantTurnosExtra>3) {
			throw new BadRequestException("El empleado ingresado ya cuenta con 3 turnos extra esta semana.");
		}
	}
	
	/*
	 * Verifica si el empleado no se pasa de los 5 turnos normales
	 * permititos por semana
	 */
	public static void validarTurnoNormalSemanal(JornadaDto jornadaDto, List<Jornada> jornadas) {
		int cantTurnosNormales = 0;
		
		cantTurnosNormales = (int) jornadas.stream().filter((j)->j.getConcept().getId()==1).count();
		if(jornadaDto.getIdConcepto()==1) {
			cantTurnosNormales++;
		}
		if(cantTurnosNormales>5) {
			throw new BadRequestException("El empleado ingresado ya cuenta con 5 turnos normales esta semana.");
		}
	}
	
	/*
	 * Verifica si el empleado no se pasa de los 2 dias libres
	 * permititos por semana
	 */
	public static void validarCantidadDiaLibreSemanal(JornadaDto jornadaDto, List<Jornada> jornadas) {
		int cantidad = 0;
		
		cantidad = (int) jornadas.stream().filter((j)->j.getConcept().getId()==3).count();
		if(jornadaDto.getIdConcepto()==3) {
			cantidad++;
		}
		if(cantidad>2) {
			throw new BadRequestException("El empleado no cuenta con más días libres esta semana.");
		}
	}
}
