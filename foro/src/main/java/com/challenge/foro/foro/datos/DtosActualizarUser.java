package com.challenge.foro.foro.datos;

import jakarta.validation.constraints.Email;

public record DtosActualizarUser(String nombre, 
    @Email(message = "Email invalido") String email) {

}
