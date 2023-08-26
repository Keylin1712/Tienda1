package com.tienda1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private long idRol;
    private String nombre;
    @Column(name = "id_usuario")
    private Long idUsuario;

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.nombre = nombre;
    }

    public Long getRutaImagen() {
        return idUsuario;
    }

    public void setRutaImagen(String rutaImagen) {
        this.idUsuario = idUsuario;
    }
}
