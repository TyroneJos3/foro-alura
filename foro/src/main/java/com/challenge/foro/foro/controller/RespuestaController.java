package com.challenge.foro.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.challenge.foro.foro.service.RespuestaService;
import com.challenge.foro.foro.datos.DtosRegistroRespuesta;
import com.challenge.foro.foro.datos.DtosDetalleRespuesta;
import com.challenge.foro.foro.datos.DtosListaRespuesta;
import com.challenge.foro.foro.datos.DtosActualizarRespuesta;

@RestController
@RequestMapping("/respuestas")
@Tag(name = "Respuestas", description = "Operaciones de respuestas del foro")
@SecurityRequirement(name = "bearer-jwt")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DtosDetalleRespuesta> crear(@Valid @RequestBody DtosRegistroRespuesta datos) {
        return ResponseEntity.ok(respuestaService.crear(datos));
    }

    @GetMapping("/tema/{temaId}")
    public ResponseEntity<Page<DtosListaRespuesta>> listarPorTema(@PathVariable Long temaId, Pageable paginacion) {
        return ResponseEntity.ok(respuestaService.listarPorTema(temaId, paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtosDetalleRespuesta> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(respuestaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtosDetalleRespuesta> actualizar(@PathVariable Long id, 
                                                           @Valid @RequestBody DtosActualizarRespuesta datos) {
        return ResponseEntity.ok(respuestaService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        respuestaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
