package com.challenge.foro.foro.datos;

import java.time.LocalDateTime;
import com.challenge.foro.foro.model.Respuesta;

public record DtosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        DtosListaUser autor
) {

    public DtosDetalleRespuesta(Respuesta respuesta) {
        this(
            respuesta.getId(),
            respuesta.getMensaje(),
            respuesta.getFechaCreacion(),
            respuesta.getSolucion(),
            new DtosListaUser(respuesta.getAutor())
        );
    }
    
}


