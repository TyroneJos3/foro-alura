package com.challenge.foro.foro.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;
import java.util.Collection;
import java.util.List;
import java.time.LocalDate;
import com.challenge.foro.foro.datos.DtosRegistroUser;
import com.challenge.foro.foro.datos.DtosActualizarUser;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    private Boolean activo = true;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now();
    }

    public Usuario(DtosRegistroUser datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.password = datos.password();
        this.activo = true;
    }

    public void actualizarDtos(DtosActualizarUser datos){
        if (datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.email() != null){
            this.email = datos.email();
        }
    }

    public void desactivar(){
        this.activo = false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
 
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
