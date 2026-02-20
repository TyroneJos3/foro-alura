package com.challenge.foro.foro.datos;

import java.time.LocalDateTime;
import com.challenge.foro.foro.model.Tema;
import com.challenge.foro.foro.model.Usuario;
import com.challenge.foro.foro.Enums.EstadoTema;

public record DtosListaTems(Long id, String titulo, String mensaje,
    LocalDateTime fechaCreaccion, EstadoTema estado, String nombreAutor) {

    public DtosListaTems(Tema tema) {
        this(tema.getId(), tema.getTitulo(), tema.getMensaje(), tema.getFechaCreaccion(), tema.getEstado(), tema.getAutor() != null ? tema.getAutor().getNombre() : null);
    }

}
