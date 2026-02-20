package com.challenge.foro.foro.datos;

import java.time.LocalDateTime;
import com.challenge.foro.foro.model.Tema;
import com.challenge.foro.foro.Enums.EstadoTema;

public record DtosDetalleTems(Long id, String titulo, String mensaje,
    LocalDateTime fechaCreaccion, EstadoTema estado, DtosListaUser autor, int respuestasCount) {

    public DtosDetalleTems(Tema tema) {
        this(
            tema.getId(),
            tema.getTitulo(),
            tema.getMensaje(),
            tema.getFechaCreaccion(),
            tema.getEstado(),
            tema.getAutor() != null ? new DtosListaUser(tema.getAutor()) : null,
            tema.getRespuestas() != null ? tema.getRespuestas().size() : 0
        );
    }


}
