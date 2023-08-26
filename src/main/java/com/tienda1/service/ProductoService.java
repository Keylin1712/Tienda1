package com.tienda1.service;

import com.tienda1.domain.Producto;
import java.util.List;

public interface ProductoService {

    public List<Producto> getProductos(boolean activos);

    //metodos CRUD
    // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);

    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);

    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);

    // se enuncia un método para recuperar los productos con una consulta ampliada
    public List<Producto> buscaProductosPorPrecioEntre(double precioInf, double precioSup);

    // se enuncia un método para recuperar los productos con una consulta JPQL
    public List<Producto> consultaJPQL(double precioInf, double precioSup);

    // se enuncia un método para recuperar los productos con una consulta SQL
    public List<Producto> consultaSQL(double precioInf, double precioSup);

}
