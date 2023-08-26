package com.tienda1.domain;

import com.tienda1.domain.Rol;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String ruta_imagen;
    private boolean activo;

    @OneToMany
    @JoinColumn(name = "id_usuario")
    List<Rol> roles;

    public void setRutaImagen(String cargaImagen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getIdUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
