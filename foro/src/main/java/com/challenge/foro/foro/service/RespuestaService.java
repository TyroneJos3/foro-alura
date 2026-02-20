package com.challenge.foro.foro.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.ValidationException;
import com.challenge.foro.foro.repository.RespuestaRepository;
import com.challenge.foro.foro.repository.TemaRepository;
import com.challenge.foro.foro.repository.UsuarioRepository;
import com.challenge.foro.foro.model.Tema;
import com.challenge.foro.foro.model.Usuario;
import com.challenge.foro.foro.model.Respuesta;
import com.challenge.foro.foro.datos.DtosRegistroRespuesta;
import com.challenge.foro.foro.datos.DtosDetalleRespuesta;
import com.challenge.foro.foro.datos.DtosListaRespuesta;
import com.challenge.foro.foro.datos.DtosActualizarRespuesta;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DtosDetalleRespuesta crear(DtosRegistroRespuesta datos) {
        Tema tema = temaRepository.findByIdAndActivoTrue(datos.temaId())
                .orElseThrow(() -> new ValidationException("Tema no encontrado"));

        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        Respuesta respuesta = new Respuesta(datos, tema, autor);
        respuesta = respuestaRepository.save(respuesta);

        return new DtosDetalleRespuesta(respuesta);
    }

    public Page<DtosListaRespuesta> listarPorTema(Long temaId, Pageable paginacion) {
        return respuestaRepository.findByTemaIdAndActivoTrue(temaId, paginacion)
                .map(DtosListaRespuesta::new);
    }

    public DtosDetalleRespuesta obtenerPorId(Long id) {
        Respuesta respuesta = respuestaRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ValidationException("Respuesta no encontrada"));
        return new DtosDetalleRespuesta(respuesta);
    }

    public DtosDetalleRespuesta actualizar(Long id, DtosActualizarRespuesta datos) {
        Respuesta respuesta = respuestaRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ValidationException("Respuesta no encontrada"));

        respuesta.actualizarDatos(datos);
        return new DtosDetalleRespuesta(respuesta);
    }

    public void eliminar(Long id) {
        Respuesta respuesta = respuestaRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ValidationException("Respuesta no encontrada"));
        respuesta.desactivar();
    }
    
}
