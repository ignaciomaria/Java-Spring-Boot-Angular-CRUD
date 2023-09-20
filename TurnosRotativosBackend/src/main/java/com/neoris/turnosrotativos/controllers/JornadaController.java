package com.neoris.turnosrotativos.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.turnosrotativos.dto.JornadaDto;
import com.neoris.turnosrotativos.service.JornadaService;

@RestController
@RequestMapping("/jornada")
public class JornadaController {
	
	@Autowired
	JornadaService jornadaService;
	
	@PostMapping
	public ResponseEntity<JornadaDto> addJornada(@Valid @RequestBody JornadaDto jornadaDto){
		JornadaDto jornada = jornadaService.addJornada(jornadaDto);
		return ResponseEntity.created(URI.create(String.format("/jornada/%d", jornada.getId()))).body(jornada);
	}
	
	@GetMapping
	public ResponseEntity<List<JornadaDto>> getJornada(@RequestParam(required = false) Integer nroDocumento
														,@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) LocalDate fecha){
		return ResponseEntity.ok(jornadaService.getJornadas(nroDocumento, fecha)); 
	}
}
