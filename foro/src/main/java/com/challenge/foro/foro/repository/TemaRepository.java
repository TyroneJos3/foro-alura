package com.challenge.foro.foro.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.challenge.foro.foro.model.Tema;
import com.challenge.foro.foro.Enums.EstadoTema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
    Page<Tema> findByActivoTrue(Pageable paginacion);
    Optional<Tema> findByIdAndActivoTrue(Long id);
    boolean existsByTitulo(String titulo);
    boolean existsByTituloAndIdNot(String titulo, Long id);
    Page<Tema> findByEstadoAndActivoTrue(EstadoTema estado, Pageable paginacion);
    Page<Tema> findByAutorIdAndActivoTrue(Long autorId, Pageable paginacion);

    @Query("SELECT t FROM Tema t WHERE t.activo = true AND " + 
        "(LOWER(t.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
        "LOWER(t.mensaje) LIKE LOWER(CONCAT('%', :filtro, '%'))) ")
    Page<Tema> buscarPorPalabra(@Param("filtro") String filtro, Pageable paginacion);

    @Query("SELECT t FROM Tema t WHERE t.activo = true ORDER BY t.fechaCreaccion DESC")
    Page<Tema> findTemasRecientes(Pageable paginacion);
     
}
