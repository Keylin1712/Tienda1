package com.tienda1.service.impl;

import com.tienda1.service.ReporteService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {
    //conexcion para la base de datos con respecto a los reportes

    @Autowired
    DataSource dataSource;

    @Override
    public ResponseEntity<Resource> generareporte(
            String reporte,
            Map<String, Object> parametros,
            String tipo) throws IOException {

        try {
//estilo es para saber donde lo queremos ver o descargar 
            String estilo;
            if (tipo.equals("vPdf")) {
                estilo = "incline; ";

            } else {
                estilo = "attachment; ";
            }
//se define la ruta donde están los reportes
            String reportePath = "reportes";
//se define el objeto donde se fenera en memoria el reporte
            ByteArrayOutputStream salida = new ByteArrayOutputStream();

//se define el lugar y acceso al archivo del reporte . jasper
            var fuente = new ClassPathResource(
                    reportePath
                    + File.separator
                    + reporte
                    + ".jasper");
//definir un objeto para leer el reporte compilado

            InputStream elReporte = fuente.getInputStream();

//se crea el reporte como tal
            var reporteJasper = JasperFillManager
                    .fillReport(elReporte, parametros, dataSource.getConnection());

// se comienza con la definicion de la respuesta al usuario 
// El medio.
            MediaType mediaType = null;

//se define el archivo de salida 
            String archivoSalida = "";

//se define un arreglo de bite para pasar el reporte a bites
            byte[] data;

//dependiendo lo seleccionado asi se genera la salida del reporte
            switch (tipo) {
                case "Pdf", "vPdf" -> {
                    JasperExportManager
                            .exportReportToPdfStream(reporteJasper, salida);
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
                case "Xls" -> {
                    JRXlsxExporter exportador = new JRXlsxExporter();
                    exportador
                            .setExporterInput(
                                    new SimpleExporterInput(reporteJasper));
                    exportador
                            .setExporterOutput(
                                    new SimpleOutputStreamExporterOutput(
                                            salida));
                    SimpleXlsxReportConfiguration configuracion
                            = new SimpleXlsxReportConfiguration();
                    configuracion.setDetectCellType(Boolean.TRUE);
                    configuracion.setCollapseRowSpan(Boolean.TRUE);

                    exportador.setConfiguration(configuracion);
                    exportador.exportReport();

                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    archivoSalida = reporte + ".xlsx";
                }
                case "Csv" -> {
                    JRCsvExporter exportador = new JRCsvExporter();
                    exportador.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    exportador.setExporterOutput(
                            new SimpleWriterExporterOutput(
                                    salida));
                    exportador.exportReport();
                    mediaType = MediaType.TEXT_PLAIN;
                    archivoSalida = reporte + ".csv";
                }

            }
// se toma el documento PDF y se transforma en bytes
            data = salida.toByteArray();

//ya se define la salida del reporte
            HttpHeaders header = new HttpHeaders();
            header.set("content-Disposition", estilo + "filename=\"" + archivoSalida + "\"");
            return ResponseEntity
                    .ok()
                    .headers(header)
                    .contentType(mediaType)
                    .body(new InputStreamResource(
                            new ByteArrayInputStream(
                                    data
                            )
                    ));

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }

        return null;
    }
}
