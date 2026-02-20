package com.challenge.foro.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.challenge.foro.foro.service.UsuarioService;
import com.challenge.foro.foro.security.TokenService;
import com.challenge.foro.foro.datos.DtosRegistroUser;
import com.challenge.foro.foro.datos.DtosDetalleUser;
import com.challenge.foro.foro.datos.DtosListaUser;
import com.challenge.foro.foro.datos.DtosActualizarUser;
import com.challenge.foro.foro.datos.DtosTokenJWT;
import com.challenge.foro.foro.model.Usuario;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones de usuarios y autenticación")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/registro")
    public ResponseEntity<DtosDetalleUser> registro(@Valid @RequestBody DtosRegistroUser datos) {
        return ResponseEntity.ok(usuarioService.crear(datos));
    }

    @PostMapping("/login")
    public ResponseEntity<DtosTokenJWT> login(@Valid @RequestBody DtosRegistroUser datos) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                datos.email(), datos.password());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var usuarioObj = (Usuario) usuarioAutenticado.getPrincipal();
        var jwtToken = tokenService.generarToken(usuarioObj);
        return ResponseEntity.ok(new DtosTokenJWT(jwtToken));
    }

    @GetMapping
    public ResponseEntity<Page<DtosListaUser>> listar(Pageable paginacion) {
        return ResponseEntity.ok(usuarioService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtosDetalleUser> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtosDetalleUser> actualizar(@PathVariable Long id, 
                                                      @Valid @RequestBody DtosActualizarUser datos) {
        return ResponseEntity.ok(usuarioService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
