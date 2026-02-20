package com.challenge.foro.foro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import com.challenge.foro.foro.repository.TemaRepository;
import com.challenge.foro.foro.repository.UsuarioRepository;
import com.challenge.foro.foro.model.Tema;
import com.challenge.foro.foro.model.Usuario;
import com.challenge.foro.foro.datos.DtosRegistroTema;
import com.challenge.foro.foro.datos.DtosDetalleTems;
import com.challenge.foro.foro.datos.DtosListaTems;
import com.challenge.foro.foro.datos.DtosActualizarTems;
import com.challenge.foro.foro.Enums.EstadoTema;

@Service
public class TemaService {
    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private UsuarioRepository usuarioRepo;

    public DtosDetalleTems crea(DtosRegistroTema data){
        if(temaRepository.existsByTitulo(data.titulo())){
            throw new ValidationException("Ya hay un tema con ese nombre");
        }

        Usuario autor = usuarioRepo.findById(data.idAutor())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        Tema tema = new Tema(data, autor);
        tema = temaRepository.save(tema);
        return new DtosDetalleTems(tema);
    }

    public Page<DtosListaTems> listar(Pageable paginacion) {
        return temaRepository.findByActivoTrue(paginacion)
                .map(DtosListaTems::new);
    }

    public DtosDetalleTems obtenerPorId(Long id) {
        Tema tema = temaRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ValidationException("Tema no encontrado"));
        return new DtosDetalleTems(tema);
    }

    public DtosDetalleTems actualizar(Long id, DtosActualizarTems datos) {
        Tema tema = temaRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ValidationException("Tema no encontrado"));

        if (datos.titulo() != null && 
            temaRepository.existsByTituloAndIdNot(datos.titulo(), id)) {
            throw new ValidationException("Ya existe otro tema con este título");
        }

        tema.actualizarDtos(datos);
        return new DtosDetalleTems(tema);
    }

    public void eliminar(Long id) {
        Tema tema = temaRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ValidationException("Tema no encontrado"));
        tema.desactivar();
    }

    public Page<DtosListaTems> buscar(String palabraClave, Pageable paginacion) {
        return temaRepository.buscarPorPalabra(palabraClave, paginacion)
                .map(DtosListaTems::new);
    }

    public Page<DtosListaTems> listarPorCurso(Long cursoId, Pageable paginacion) {
        return temaRepository.findByAutorIdAndActivoTrue(cursoId, paginacion)
                .map(DtosListaTems::new);
    }

    public Page<DtosListaTems> listarPorStatus(EstadoTema status, Pageable paginacion) {
        return temaRepository.findByEstadoAndActivoTrue(status, paginacion)
                .map(DtosListaTems::new);
    }
    
}
