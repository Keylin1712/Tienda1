package com.tienda1.controller;

import com.tienda1.service.ReporteService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/principal")
    public String principal(Model model) {
        return "/reportes/principal";
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Resource> usuarios(@RequestParam String tipo) throws IOException {
        var reporte = "usuarios";
        return reporteService.generareporte(reporte, null, tipo);
    }

    @GetMapping("/ventas")
    public ResponseEntity<Resource> ventas(@RequestParam String tipo) throws IOException {
        var reporte = "ventas";
        return reporteService.generareporte(reporte, null, tipo);
    }

    @GetMapping("/factura")
    public ResponseEntity<Resource> factura(@RequestParam String tipo) throws IOException {
        var reporte = "factura";
        return reporteService.generareporte(reporte, null, tipo);
    }

    @GetMapping("/produc")
    public ResponseEntity<Resource> produc(@RequestParam String tipo) throws IOException {
        var reporte = "produc";
        return reporteService.generareporte(reporte, null, tipo);
    }
}
