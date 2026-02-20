package com.challenge.foro.foro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.foro.foro.model.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Page<Respuesta> findByActivoTrue(Pageable paginacion);
    Optional<Respuesta> findByIdAndActivoTrue(Long id);
    Page<Respuesta> findByTemaIdAndActivoTrue(Long temaId, Pageable paginacion);
    List<Respuesta> findByTemaIdAndActivoTrueOrderByFechaCreacionAsc(Long temaId);
    Page<Respuesta> findByAutorIdAndActivoTrue(Long autorId, Pageable paginacion);
    Long countByTemaIdAndActivoTrue(Long temaId);
    Optional<Respuesta> findByTemaIdAndSolucionTrueAndActivoTrue(Long temaId);
}
