package com.tienda1.service;

import com.tienda1.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    //El siguiente método retorna una lisra con las categorías
   //Que están en la tabla categoría, todas o sólo las activas
    public List<Categoria> getCategoria(boolean activos);
    
    //Acá siguen los métodos para hacer un CRUD de la tabla categoría
}
