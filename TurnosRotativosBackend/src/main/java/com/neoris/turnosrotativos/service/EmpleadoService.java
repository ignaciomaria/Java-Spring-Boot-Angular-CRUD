package com.neoris.turnosrotativos.service;

import java.util.List;

import com.neoris.turnosrotativos.dto.EmpleadoDto;

public interface EmpleadoService {
	
	public EmpleadoDto addEmpleado(EmpleadoDto empleadoDto);
	
	public List<EmpleadoDto> getEmpleados();
	
	public EmpleadoDto getEmpleadoById(Integer id);
	
	public void deleteEmpleadoById(Integer id);
	
	public EmpleadoDto updateEmpleado(EmpleadoDto empleadoDto, Integer id);
}
