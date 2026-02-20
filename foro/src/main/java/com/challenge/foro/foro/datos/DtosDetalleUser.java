package com.challenge.foro.foro.datos;

import java.time.LocalDateTime;
import com.challenge.foro.foro.model.Usuario;

public record DtosDetalleUser(Long id,String nombre, String email, LocalDateTime fecha, Boolean activo) {

    public DtosDetalleUser(Usuario usu){
        this(usu.getId(),
                usu.getNombre(),
                usu.getEmail(),
                usu.getFechaRegistro() != null ? usu.getFechaRegistro().atStartOfDay() : null,
                usu.getActivo());
    }
    
}
