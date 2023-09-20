package com.neoris.turnosrotativos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.turnosrotativos.dto.EmpleadoDto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.NotFoundException;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import com.neoris.turnosrotativos.repository.JornadaRepository;
import com.neoris.turnosrotativos.service.EmpleadoService;
import com.neoris.turnosrotativos.validations.CustomValidationEmpleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired
	EmpleadoRepository empleadoRepo;
	
	@Autowired
	JornadaRepository jornadaRepo;
	
	/*
	 * Traer la lista de todos los empleados y exponerlos como DTO
	 */
	@Override
	public List<EmpleadoDto> getEmpleados() {
		// TODO Auto-generated method stub
		List<Empleado> empleados = empleadoRepo.findAll();
		List<EmpleadoDto> empleadosDto = new ArrayList<EmpleadoDto>();
		
		empleados.stream().forEach((e)->empleadosDto.add(new EmpleadoDto().toDto(e)));
		return empleadosDto;
	}
	
	/*
	 * Traer empleado por Id y lo convierte en DTO
	 */
	@Override
	public EmpleadoDto getEmpleadoById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Empleado> empleado = empleadoRepo.findById(id);
		if(empleado.isEmpty()) {
			throw new NotFoundException("No se encontró el empleado con Id: "+id);
		}
		return new EmpleadoDto().toDto(empleado.get());
	}
	
	/*
	 * Eliminar empleado por Id y sus jornadas hasta la fecha
	 */
	@Override
	public void deleteEmpleadoById(Integer id) {
		// TODO Auto-generated method stub
		List<Jornada> jornadas = jornadaRepo.findAllByNroDocumento(getEmpleadoById(id).getNroDocumento());
		jornadas.stream().forEach((j)->jornadaRepo.delete(j)); //Elimino jornadas del empleado
		empleadoRepo.deleteById(id); //Elimino empleado
	}
	
	/*
	 * Registrar un empleado
	 */
	@Override
	public EmpleadoDto addEmpleado(EmpleadoDto empleadoDto) {
		// TODO Auto-generated method stub
		Optional<Empleado> traerEmpleadoPorDocumento = empleadoRepo.findByNroDocumento(empleadoDto.getNroDocumento());
		Optional<Empleado> traerEmpleadoPorEmail = empleadoRepo.findByEmail(empleadoDto.getEmail());
		CustomValidationEmpleado.validacionesGenerales(empleadoDto, traerEmpleadoPorDocumento, traerEmpleadoPorEmail);
		return empleadoDto.toDto(empleadoRepo.save(empleadoDto.toEntity()));
	}
	
	/*
	 * Actualiza empleado por Id
	 */
	@Override
	public EmpleadoDto updateEmpleado(EmpleadoDto empleadoDto, Integer id) {
		// TODO Auto-generated method stub
		Optional<Empleado> traerEmpleadoPorDocumento = empleadoRepo.findByNroDocumento(empleadoDto.getNroDocumento());
		Optional<Empleado> traerEmpleadoPorEmail = empleadoRepo.findByEmail(empleadoDto.getEmail());
		Optional<Empleado> empleado = empleadoRepo.findById(id);
		if(!empleado.isPresent()) {
			throw new NotFoundException("No se encontró el empleado con Id "+id);
		}
		CustomValidationEmpleado.validacionesGenerales(empleadoDto, traerEmpleadoPorDocumento, traerEmpleadoPorEmail);
		Empleado empleadoEntity = empleadoDto.toEntity();
		empleadoEntity.setId(empleado.get().getId()); //Conectar la entidad a modificar con el DTO entrante
		empleadoEntity.setFechaCreacion(empleado.get().getFechaCreacion()); //La fecha de creacion es la misma
		return empleadoDto.toDto(empleadoRepo.save(empleadoEntity));
	}
	
}
