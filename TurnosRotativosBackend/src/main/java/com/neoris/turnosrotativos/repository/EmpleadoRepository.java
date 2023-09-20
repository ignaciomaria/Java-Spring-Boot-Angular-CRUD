package com.neoris.turnosrotativos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.turnosrotativos.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	public Optional<Empleado> findByNroDocumento(Integer nroDocumento);
	
	public Optional<Empleado> findByEmail(String email);
}
