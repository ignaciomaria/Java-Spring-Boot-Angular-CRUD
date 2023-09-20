package com.neoris.turnosrotativos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neoris.turnosrotativos.entities.Jornada;

public interface JornadaRepository extends JpaRepository<Jornada, Integer>{
	
	public Optional<Jornada> findByFecha(LocalDate fecha);
	
	/*
	 * Busca en la base de datos una lista de jornadas
	 * trascurridas en una misma semana
	 */
	@Query("FROM jornadas j WHERE j.nroDocumento = :nroDocumento AND j.fecha BETWEEN :inicio AND :fin")
	public List<Jornada> findWeekByNroDocumento(@Param("nroDocumento") Integer nroDocumento, 
										@Param("inicio") LocalDate inicioSemana, 
										@Param("fin") LocalDate finSemana);
	
	List<Jornada> findAllByNroDocumento(Integer nroDocumento);

    List<Jornada> findAllByFecha(LocalDate fecha);

    List<Jornada> findAllByNroDocumentoAndFecha(Integer nroDocumento, LocalDate fecha);
}
