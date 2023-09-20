package com.neoris.turnosrotativos.controllers;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.turnosrotativos.dto.EmpleadoDto;
import com.neoris.turnosrotativos.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
@CrossOrigin
public class EmpleadoController {
	
	@Autowired
	EmpleadoService empleadoService;
	
	@PostMapping
	public ResponseEntity<EmpleadoDto> addEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto){
		EmpleadoDto empleado = empleadoService.addEmpleado(empleadoDto);
		return ResponseEntity.created(URI.create(String.format("/empleado/%d", empleado.getId()))).body(empleado);
	}
	
	
	@GetMapping
	public ResponseEntity<List<EmpleadoDto>> getEmpleados(){
		return ResponseEntity.ok(empleadoService.getEmpleados());
	}
	
	@GetMapping("/{empleadoId}")
	public ResponseEntity<EmpleadoDto> getEmpleadoById(@PathVariable("empleadoId") Integer id){
		return ResponseEntity.ok(empleadoService.getEmpleadoById(id));
	}
	
	@DeleteMapping("/{empleadoId}")
	public ResponseEntity<Object> deleteEmpleadoById(@PathVariable("empleadoId") Integer id){
		empleadoService.deleteEmpleadoById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@PutMapping("/{empleadoId}")
	public ResponseEntity<Object> updateEmpleadoById(@PathVariable("empleadoId") Integer id, 
													@Valid @RequestBody EmpleadoDto empleadoDto){
		return ResponseEntity.ok(empleadoService.updateEmpleado(empleadoDto, id));
	}
}
