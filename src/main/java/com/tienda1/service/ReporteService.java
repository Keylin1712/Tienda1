package com.tienda1.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface ReporteService {

    public ResponseEntity<Resource> generareporte(
            String reporte, //nombre del archivo que tiene el reporte .jasper
            Map<String, Object> parametros,//parametros si el reporte ocupa parámetros
            String tipo //tipo de reporte PDF,XLS,CSV ENTRE OTROS
    ) throws IOException;
}
