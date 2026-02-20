package com.challenge.foro.foro.datos;

import java.time.LocalDateTime;
import com.challenge.foro.foro.model.Respuesta;

public record DtosListaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autorNombre,
        Boolean solucion
) {
    public DtosListaRespuesta(Respuesta respuesta) {
        this(
            respuesta.getId(),
            respuesta.getMensaje(),
            respuesta.getFechaCreacion(),
            respuesta.getAutor() != null ? respuesta.getAutor().getNombre() : null,
            respuesta.getSolucion()
        );
    }
}


