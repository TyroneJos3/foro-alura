package com.challenge.foro.foro.repository;

import com.challenge.foro.foro.model.Usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndActivoTrue(String email);
    Page<Usuario> findByActivoTrue(Pageable paginacion);
    boolean existsByEmail(String email);
}
