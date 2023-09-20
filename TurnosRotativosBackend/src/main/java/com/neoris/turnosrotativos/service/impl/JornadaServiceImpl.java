package com.neoris.turnosrotativos.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.turnosrotativos.dto.JornadaDto;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repository.ConceptoRepository;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import com.neoris.turnosrotativos.repository.JornadaRepository;
import com.neoris.turnosrotativos.service.JornadaService;
import com.neoris.turnosrotativos.validations.CustomValidationJornada;

@Service
public class JornadaServiceImpl implements JornadaService{
	
	@Autowired
	JornadaRepository jornadaRepo;
	@Autowired
	ConceptoRepository conceptoRepo;
	@Autowired
	EmpleadoRepository empleadoRepo;
	
	/*
	 * Registra una jornada
	 * Se trae empleado y concepto segun los id de la jornada ingresada
	 * Luego realizan las respectivas validaciones
	 * Por ultimo se crea una Entity jornada para agregarle los campos que no 
	 * ingresan por parametro, y poder enviarla al repository
	 */
	public JornadaDto addJornada(JornadaDto jornadaDto) {
		Optional<Empleado> empleado = empleadoRepo.findById(jornadaDto.getIdEmpleado());
		Optional<Concepto> concepto = conceptoRepo.findById(jornadaDto.getIdConcepto());
		Optional<Jornada> jornada = jornadaRepo.findByFecha(jornadaDto.getFecha());
		
		//---------------------Validaciones---------------------------------
		CustomValidationJornada.validarEmpleadoConceptoExist(empleado, concepto);
		CustomValidationJornada.validarHsTrabajadasConceptos(jornadaDto, concepto);
		CustomValidationJornada.validarEmpleadoHsJornada(jornadaDto, empleado, concepto);
		CustomValidationJornada.validarEmpleadoDiaLibre(empleado, jornada);
		CustomValidationJornada.validarEmpleadoMismoConcepto(empleado, concepto, jornada);
		CustomValidationJornada.validarTurnoNormalMasExtra(jornadaDto, jornada);
		CustomValidationJornada.validarDiaLibreDeMas(jornadaDto, jornada);
		LocalDate inicioSemana = jornadaDto.getFecha().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate finSemana = inicioSemana.plusDays(6);
		List<Jornada> jornadas = jornadaRepo.findWeekByNroDocumento(empleado.get().getNroDocumento(), inicioSemana, finSemana);
		CustomValidationJornada.validarHsSemanales(jornadaDto, jornadas);
		CustomValidationJornada.validarTurnosExtraSemanal(jornadaDto, jornadas);
		CustomValidationJornada.validarTurnoNormalSemanal(jornadaDto, jornadas);
		CustomValidationJornada.validarCantidadDiaLibreSemanal(jornadaDto, jornadas);
		//------------------------------------------------------------------
		Jornada jornadaEntity = jornadaDto.toEntity(empleado.get(), concepto.get()); 
		jornadaEntity.setNroDocumento(empleadoRepo.findById(jornadaDto.getIdEmpleado()).get().getNroDocumento());
		jornadaEntity.setNombreCompleto(empleadoRepo.findById(jornadaDto.getIdEmpleado()).get().getNombre()+" "
										+empleadoRepo.findById(jornadaDto.getIdEmpleado()).get().getApellido());
		jornadaEntity.setConcepto(conceptoRepo.findById(jornadaDto.getIdConcepto()).get().getNombre());
		
		return jornadaDto.toDto(jornadaRepo.save(jornadaEntity));
	}
	
	/*
	 * Trae jornadas segun los parametros entrantes
	 */
	public List<JornadaDto> getJornadas(Integer nroDocumento, LocalDate fecha){
		List<Jornada> jornadas = null;
		List<JornadaDto> jornadasDto = new ArrayList<JornadaDto>();
		
		if(nroDocumento==null && fecha==null) {
			jornadas = jornadaRepo.findAll();
		}else if(nroDocumento!=null && fecha==null) {
			jornadas = jornadaRepo.findAllByNroDocumento(nroDocumento);
		}else if(nroDocumento==null && fecha!=null) {
			jornadas = jornadaRepo.findAllByFecha(fecha);
		}else if(nroDocumento!=null && fecha!=null) {
			jornadas = jornadaRepo.findAllByNroDocumentoAndFecha(nroDocumento, fecha);
		}
		
		jornadas.stream().forEach((j)->jornadasDto.add(new JornadaDto().toDto(j)));
		
		return jornadasDto;
	}

}
