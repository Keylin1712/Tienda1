package com.tienda1.service;

import com.tienda1.domain.Producto;
import java.util.List;

public interface ProductoService {

    //El siguiente método retorna una lisra con las categorías
    //Que están en la tabla categoría, todas o sólo las activas
    public List<Producto> getProductos(boolean activos);

    //Acá siguen los métodos para hacer un CRUD de la tabla categoría
   // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);

    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);

    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);
}
