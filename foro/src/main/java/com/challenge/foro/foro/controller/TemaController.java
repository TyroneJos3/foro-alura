package com.challenge.foro.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.challenge.foro.foro.service.TemaService;
import com.challenge.foro.foro.datos.DtosRegistroTema;
import com.challenge.foro.foro.datos.DtosDetalleTems;
import com.challenge.foro.foro.datos.DtosListaTems;
import com.challenge.foro.foro.datos.DtosActualizarTems;
import com.challenge.foro.foro.Enums.EstadoTema;

@RestController
@RequestMapping("/temas")
@Tag(name = "Temas", description = "Operaciones de temas del foro")
@SecurityRequirement(name = "bearer-jwt")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @PostMapping
    public ResponseEntity<DtosDetalleTems> crear(@Valid @RequestBody DtosRegistroTema datos) {
        return ResponseEntity.ok(temaService.crea(datos));
    }

    @GetMapping
    public ResponseEntity<Page<DtosListaTems>> listar(Pageable paginacion) {
        return ResponseEntity.ok(temaService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtosDetalleTems> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(temaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtosDetalleTems> actualizar(@PathVariable Long id, 
                                                      @Valid @RequestBody DtosActualizarTems datos) {
        return ResponseEntity.ok(temaService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        temaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<DtosListaTems>> buscar(@RequestParam String palabraClave, Pageable paginacion) {
        return ResponseEntity.ok(temaService.buscar(palabraClave, paginacion));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Page<DtosListaTems>> listarPorStatus(@PathVariable EstadoTema estado, Pageable paginacion) {
        return ResponseEntity.ok(temaService.listarPorStatus(estado, paginacion));
    }
}
