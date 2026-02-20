package com.challenge.foro.foro.datos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtosRegistroUser(
    @NotBlank(message = "Nombre obligatorio") String nombre,
    @NotBlank(message = "Email obligatorio") 
    @Email(message = "Formato de email invalido") String email,
    @NotBlank(message = "Contraseña obligatoria") 
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String password){

    }
