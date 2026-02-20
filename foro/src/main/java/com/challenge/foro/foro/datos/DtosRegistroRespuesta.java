package com.challenge.foro.foro.datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtosRegistroRespuesta(
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,

        @NotNull(message = "El ID del tópico es obligatorio")
        Long temaId,

        @NotNull(message = "El ID del autor es obligatorio")
        Long autorId
) {
    
}
