package com.neoris.turnosrotativos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.turnosrotativos.dto.ConceptoDto;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.repository.ConceptoRepository;
import com.neoris.turnosrotativos.service.ConceptoService;

@Service
public class ConceptoServiceImpl implements ConceptoService{

	@Autowired
	ConceptoRepository conceptoRepo;
	
	/*
	 * Traer todos los conceptos y exponerlos como DTO
	 */
	@Override
	public List<ConceptoDto> getConceptos() {
		// TODO Auto-generated method stub
		List<Concepto> conceptos = conceptoRepo.findAll();
		List<ConceptoDto> conceptosDto = new ArrayList<ConceptoDto>();
		
		conceptos.stream().forEach((c)->conceptosDto.add(new ConceptoDto().toDto(c)));
		/*
		for(Concepto c: conceptos) {
			ConceptoDto conceptoDto = new ConceptoDto();
			conceptosDto.add(conceptoDto.toDto(c));
		}*/
		return conceptosDto; 
	}
	
	
}
