package com.challenge.foro.foro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.challenge.foro.foro.Enums.EstadoTema;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Temas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creaccion")
    private LocalDateTime fechaCreaccion;
    
    @Enumerated(EnumType.STRING)
    private EstadoTema estado = EstadoTema.ABIERTO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private Usuario autor;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();

    private Boolean activo = true;

    @PrePersist
    protected void onCreate(){
        fechaCreaccion = LocalDateTime.now();
    }

    public Tema(com.challenge.foro.foro.datos.DtosRegistroTema datos, Usuario autor){
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.estado = EstadoTema.ABIERTO;
        this.activo = true;
    }

    public void actualizarDtos(com.challenge.foro.foro.datos.DtosActualizarTems datos){
        if (datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
        if (datos.estado() != null){
            this.estado = datos.estado();
        }
    }

    public void desactivar(){
        this.activo = false;
    }

    public void cerra(){
        this.estado = EstadoTema.CERRADO;
    }

    public void arreglar(){
        this.estado = EstadoTema.SOLUCIONADO;
    }

    
    
}
