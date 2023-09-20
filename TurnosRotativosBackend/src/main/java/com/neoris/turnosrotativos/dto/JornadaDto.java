package com.neoris.turnosrotativos.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;

@Component
public class JornadaDto {
	
	private Integer id;
	@NotNull(message = "idEmpleado es obligatorio.")
	private Integer idEmpleado;
	private Integer nroDocumento;
	private String nombreCompleto;
	@NotNull(message = "idConcepto es obligatorio.")
	private Integer idConcepto;
	private String concepto;
	@NotNull(message = "fecha es obligatorio.")
	private LocalDate fecha;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer horasTrabajadas;
	
	/*
     * Convierte un DTO en una Entity
     */
	public Jornada toEntity(Empleado empleado, Concepto concepto) {
		Jornada jornada = new Jornada();
		jornada.setEmpleado(empleado);
		jornada.setConcept(concepto);
		jornada.setFecha(this.getFecha());
		jornada.setHorasTrabajadas(this.getHorasTrabajadas());
		return jornada;
	}
	
	/*
     * Convierte una Entity en un DTO
     */
	public JornadaDto toDto(Jornada jornada) {
		this.setId(jornada.getId());
		this.setNroDocumento(jornada.getNroDocumento());
		this.setNombreCompleto(jornada.getNombreCompleto());
		this.setFecha(jornada.getFecha());
		this.setConcepto(jornada.getConcepto());
		this.setIdEmpleado(jornada.getEmpleado().getId());
		this.setIdConcepto(jornada.getConcept().getId());
		this.setHorasTrabajadas(jornada.getHorasTrabajadas());
		return this;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Integer getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Integer getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Integer getHorasTrabajadas() {
		return horasTrabajadas;
	}
	public void setHorasTrabajadas(Integer horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}
}
