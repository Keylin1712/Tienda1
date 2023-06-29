package com.tienda1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")

public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
      
    private Long idCategoria;
    private String descripcion;
    private String ruta_imagen;
    private String activo;

    public Categoria() {
    }

    public Categoria(String descripcion, String activo) {
        this.descripcion = descripcion;
        this.activo = activo;
    }   

    public boolean isActivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
