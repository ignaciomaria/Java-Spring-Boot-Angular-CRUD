package com.neoris.turnosrotativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.turnosrotativos.dto.ConceptoDto;
import com.neoris.turnosrotativos.service.ConceptoService;

@RestController
@RequestMapping("/concepto")
public class ConceptoController {
	
	@Autowired
	ConceptoService conceptoService;
	
	@GetMapping
	public ResponseEntity<List<ConceptoDto>> getConceptos(){
		return ResponseEntity.ok(conceptoService.getConceptos());
	}
}
