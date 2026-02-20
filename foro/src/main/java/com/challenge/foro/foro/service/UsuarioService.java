package com.challenge.foro.foro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import com.challenge.foro.foro.repository.UsuarioRepository;
import com.challenge.foro.foro.model.Usuario;
import com.challenge.foro.foro.datos.DtosRegistroUser;
import com.challenge.foro.foro.datos.DtosDetalleUser;
import com.challenge.foro.foro.datos.DtosListaUser;
import com.challenge.foro.foro.datos.DtosActualizarUser;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DtosDetalleUser crear(DtosRegistroUser datos) {
        if (usuarioRepository.existsByEmail(datos.email())) {
            throw new ValidationException("El email ya está registrado");
        }

        Usuario usuario = new Usuario(datos);
        usuario.setPassword(passwordEncoder.encode(datos.password()));
        usuario = usuarioRepository.save(usuario);

        return new DtosDetalleUser(usuario);
    }

    public Page<DtosListaUser> listar(Pageable paginacion) {
        return usuarioRepository.findByActivoTrue(paginacion)
                .map(DtosListaUser::new);
    }

    public DtosDetalleUser obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));
        return new DtosDetalleUser(usuario);
    }

    public DtosDetalleUser actualizar(Long id, DtosActualizarUser datos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        if (datos.email() != null && !datos.email().equals(usuario.getEmail()) &&
            usuarioRepository.existsByEmail(datos.email())) {
            throw new ValidationException("El email ya está registrado");
        }

        usuario.actualizarDtos(datos);
        return new DtosDetalleUser(usuario);
    }

    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));
        usuario.desactivar();
    }
}
