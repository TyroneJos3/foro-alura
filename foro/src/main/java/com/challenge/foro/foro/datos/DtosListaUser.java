package com.challenge.foro.foro.datos;

import com.challenge.foro.foro.model.Usuario;

public record DtosListaUser(Long id, String nombre, String email) {
    public DtosListaUser(Usuario usua){
        this(usua.getId(), usua.getNombre(), usua.getEmail());
    }
}
