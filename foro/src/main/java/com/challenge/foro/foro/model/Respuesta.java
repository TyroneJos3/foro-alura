package com.challenge.foro.foro.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.challenge.foro.foro.datos.DtosRegistroRespuesta;
import com.challenge.foro.foro.datos.DtosActualizarRespuesta;

@Entity
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tema_id", nullable = false)
    private Tema tema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private Usuario autor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Boolean solucion = false;

    private Boolean activo = true;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    public Respuesta(DtosRegistroRespuesta datos, Tema tema, Usuario autor) {
        this.mensaje = datos.mensaje();
        this.tema = tema;
        this.autor = autor;
        this.solucion = false;
        this.activo = true;
    }

    public void actualizarDatos(DtosActualizarRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.solucion() != null) {
            this.solucion = datos.solucion();
        }
    }

    public void desactivar() {
        this.activo = false;
    }

    public void marcarComoSolucion() {
        this.solucion = true;
    }
    
}
