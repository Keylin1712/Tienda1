package com.tienda1.service;

import com.tienda1.domain.Categoria;
import java.util.List;

public interface CategoriaService {

    //El siguiente método retorna una lisra con las categorías
    //Que están en la tabla categoría, todas o sólo las activas
    public List<Categoria> getCategorias(boolean activos);

    //Acá siguen los métodos para hacer un CRUD de la tabla categoría
// Se obtiene un Categoria, a partir del id de un categoria
    public Categoria getCategoria(Categoria categoria);

    // Se inserta un nuevo categoria si el id del categoria esta vacío
    // Se actualiza un categoria si el id del categoria NO esta vacío
    public void save(Categoria categoria);

    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Categoria categoria);
}