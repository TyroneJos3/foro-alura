package com.challenge.foro.foro.datos;

import com.challenge.foro.foro.Enums.EstadoTema;

public record DtosActualizarTems(
    String titulo, String mensaje, EstadoTema estado) {

}
