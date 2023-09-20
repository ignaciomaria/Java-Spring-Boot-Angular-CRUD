package com.neoris.turnosrotativos.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import com.neoris.turnosrotativos.entities.Empleado;

@Component
public class EmpleadoDto {
	
	private Integer id;
	@NotNull (message = "Documento es obligatorio.")
	private Integer nroDocumento;
	@NotNull (message = "Nombre no puede ser nulo")
    @NotBlank (message = "Nombre es obligatorio.")
	@Pattern(regexp = "^[ A-ZÑa-zñáéíóúÁÉÍÓÚ/'/`]+$", message = "Solo se permiten letras en el campo ‘nombre’.")
	private String nombre;
	@NotNull (message = "Apellido no puede ser nulo")
    @NotBlank (message = "Apellido es obligatorio.")
	@Pattern(regexp = "^[ A-ZÑa-zñáéíóúÁÉÍÓÚ/'/`]+$", message = "Solo se permiten letras en el campo ‘apellido’.")
	private String apellido;
	@NotNull (message = "Email no puede ser nulo")
    @NotBlank (message = "Email es obligatorio.")
	@Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "El email ingresado no es correcto.")
	private String email;
	@NotNull (message = "La fecha de nacimiento es obligatoria.")
	private LocalDate fechaNacimiento;
	@NotNull (message = "La fecha de ingreso es obligatoria.")
	private LocalDate fechaIngreso;
	private LocalDate fechaCreacion;
	
	/*
     * Convierte un DTO en una Entity
     */
	public Empleado toEntity() {
		Empleado empleado = new Empleado();
		empleado.setNombre(this.getNombre());
		empleado.setApellido(this.getApellido());
		empleado.setNroDocumento(this.getNroDocumento());
		empleado.setEmail(this.getEmail());
		empleado.setFechaNacimiento(this.getFechaNacimiento());
		empleado.setFechaIngreso(this.getFechaIngreso());
		return empleado;
	}
	
	/*
     * Convierte una Entity en un DTO
     */
	public EmpleadoDto toDto(Empleado empleado) {
		this.setId(empleado.getId());
		this.setNombre(empleado.getNombre());
		this.setApellido(empleado.getApellido());
		this.setNroDocumento(empleado.getNroDocumento());
		this.setEmail(empleado.getEmail());
		this.setFechaNacimiento(empleado.getFechaNacimiento());
		this.setFechaIngreso(empleado.getFechaIngreso());
		this.setFechaCreacion(empleado.getFechaCreacion());
		return this;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
