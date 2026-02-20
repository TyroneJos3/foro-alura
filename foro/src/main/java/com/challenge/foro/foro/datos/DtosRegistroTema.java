package com.challenge.foro.foro.datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtosRegistroTema(
    @NotBlank(message = "Titulo obligatorio") String titulo,
    @NotBlank(message = "Mensaje obligatorio") String mensaje,
    @NotNull(message = "Id del autor obligatorio") Long idAutor
) {

}
