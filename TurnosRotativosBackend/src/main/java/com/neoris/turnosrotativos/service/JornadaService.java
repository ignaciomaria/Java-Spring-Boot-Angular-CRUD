package com.neoris.turnosrotativos.service;

import java.time.LocalDate;
import java.util.List;

import com.neoris.turnosrotativos.dto.JornadaDto;

public interface JornadaService {
	
	public JornadaDto addJornada(JornadaDto jornadaDto);
	
	public List<JornadaDto> getJornadas(Integer nroDocumento, LocalDate fecha);
	
}
