package com.tienda1.service.impl;

import com.tienda1.dao.CategoriaDao;
import com.tienda1.domain.Categoria;
import com.tienda1.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    //La anotación Autowired crea un único objeto sin hacer new... y se mantiene
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDao.findAll();
        if (activos) { //Si quiere sólo las cateogirías activas
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
}
