package com.neoris.turnosrotativos.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neoris.turnosrotativos.entities.Concepto;

@Component
public class ConceptoDto {
	
	private Integer id;
    private String nombre;
    private Boolean laborable;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hsMinimo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hsMaximo;
    
    /*
     * Convierte un DTO en una Entity
     */
    public Concepto toEntity() {
    	Concepto entity = new Concepto();
    	entity.setId(this.getId());
    	entity.setNombre(this.getNombre());
    	entity.setLaborable(this.getLaborable());
    	entity.setHsMinimo(this.getHsMinimo());
    	entity.setHsMaximo(this.getHsMaximo());
    	return entity;
    }
    
    /*
     * Convierte una Entity en un DTO
     */
    public ConceptoDto toDto(Concepto concepto) {
    	this.setId(concepto.getId());
    	this.setNombre(concepto.getNombre());
    	this.setLaborable(concepto.getLaborable());
    	this.setHsMinimo(concepto.getHsMinimo());
    	this.setHsMaximo(concepto.getHsMaximo());
    	return this;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getLaborable() {
        return laborable;
    }

    public void setLaborable(Boolean laborable) {
        this.laborable = laborable;
    }

	public Integer getHsMinimo() {
		return hsMinimo;
	}

	public void setHsMinimo(Integer hsMinimo) {
		this.hsMinimo = hsMinimo;
	}

	public Integer getHsMaximo() {
		return hsMaximo;
	}

	public void setHsMaximo(Integer hsMaximo) {
		this.hsMaximo = hsMaximo;
	}
}
