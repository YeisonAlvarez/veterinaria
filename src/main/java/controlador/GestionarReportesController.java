package controlador;

import java.awt.Desktop; // Importa la clase Desktop
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Constantes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



import conexionBD.ConexionBD;

public class GestionarReportesController implements Initializable {

    @FXML private Button btnAtras;
    @FXML private Button btnListaAfiliados;
    @FXML private Button btnListaMascotas;
    @FXML private Button btnListabtnListaBeneficios;
    
    
    @FXML
    void btnListaAfiliadosAction() {
        // Crear el documento PDF
        Document documento = new Document();
        // Ruta personalizada para guardar el PDF
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporte_afiliados.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));
            
            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n reporte de los afiliados \n\n");
            
            documento.open();
            
            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 3 columnas
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Cedula");
            tabla.addCell("Nombre");
            tabla.addCell("Segundo Nombre");

            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaAfiliados(tabla)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporte_afiliados.pdf");
            if (archivoPDF.exists()) {
                // Abre el archivo PDF con la aplicación predeterminada
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para llenar la tabla con datos de la base de datos
    private boolean llenarTablaAfiliados(PdfPTable tabla) {
        boolean exito = false;
        String query = "SELECT p.cedula, p.primerNombre, p.segundoNombre\r\n"
        		+ "FROM persona p\r\n"
        		+ "JOIN tipo_vinculo tv ON p.codigo_vinculo = tv.idtipo_vinculo\r\n"
        		+ "WHERE tv.nombre_vinculo = 'afiliado';\r\n"
        		+ "";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                tabla.addCell(rs.getString("cedula"));
                tabla.addCell(rs.getString("primerNombre"));
                tabla.addCell(rs.getString("segundoNombre"));
            }
            exito = true; // Si llega aquí, el proceso fue exitoso

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    
//                              Mascotas 
    
    
   
    @FXML
    void btnListaMascotasAction() {
        // Crear el documento PDF
        Document documento = new Document();
        // Ruta personalizada para guardar el PDF
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporteMascotas.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));
            
            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n reporte de las Mascotas \n\n");
            
            documento.open();
            
            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 3 columnas
            PdfPTable tabla = new PdfPTable(8);
            tabla.addCell("codigo");
            tabla.addCell("nombre");
            tabla.addCell("peso");
            tabla.addCell("fechaNacimiento");
            tabla.addCell("especie_codigo");
            tabla.addCell("Raza_codigo");
            tabla.addCell("HistoriaClinica_codigo");
            tabla.addCell("cedula_persona");

            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaMascotas(tabla)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporteMascotas.pdf");
            if (archivoPDF.exists()) {
                // Abre el archivo PDF con la aplicación predeterminada
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para llenar la tabla con datos de la base de datos
    private boolean llenarTablaMascotas(PdfPTable tabla) {
        boolean exito = false;
        String query = "SELECT codigo, nombre, peso, fechaNacimiento, especie_codigo, Raza_codigo, HistoriaClinica_codigo, cedula_persona  FROM mascota";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                tabla.addCell(rs.getString("codigo"));
                tabla.addCell(rs.getString("nombre"));
                tabla.addCell(rs.getString("peso"));
                tabla.addCell(rs.getString("fechaNacimiento"));
                tabla.addCell(rs.getString("especie_codigo"));
                tabla.addCell(rs.getString("Raza_codigo"));
                tabla.addCell(rs.getString("HistoriaClinica_codigo"));
                tabla.addCell(rs.getString("cedula_persona"));
                
                
            }
            exito = true; // Si llega aquí, el proceso fue exitoso

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }
    
    
    
// 								 Beneficios
    
    
    @FXML
    void btnListaBeneficiosAction() {
        // Crear el documento PDF
        Document documento = new Document();
        // Ruta personalizada para guardar el PDF
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporteBeneficios.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));
            
            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n reporte de los Beneficios \n\n");
            
            documento.open();
            
            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 3 columnas
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("codigo");
            tabla.addCell("nombre");
            tabla.addCell("descripcion");
            tabla.addCell("costo");
            tabla.addCell("idTipo_Beneficio");

            
            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaBeneficios(tabla)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporteBeneficios.pdf");
            if (archivoPDF.exists()) {
                // Abre el archivo PDF con la aplicación predeterminada
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para llenar la tabla con datos de la base de datos
    private boolean llenarTablaBeneficios(PdfPTable tabla) {
        boolean exito = false;
        String query = "SELECT codigo, nombre, descripcion, costo, idTipo_Beneficio  FROM beneficio";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                tabla.addCell(rs.getString("codigo"));
                tabla.addCell(rs.getString("nombre"));
                tabla.addCell(rs.getString("descripcion"));
                tabla.addCell(rs.getString("costo"));
                tabla.addCell(rs.getString("idTipo_Beneficio"));
            
            }
            exito = true; // Si llega aquí, el proceso fue exitoso

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }
    
    
    
    
    
    
    
    
    
//		Obtener el total de contratos firmados por un ejecutivo.
 
    @FXML
    void btnListaContratosPorEjecutivoAction() {
        // Pedir el idEjecutivo al usuario
        String idEjecutivoStr = JOptionPane.showInputDialog("Ingrese el ID del Ejecutivo:");
        
        // Validar que el idEjecutivo no sea nulo o vacío
        if (idEjecutivoStr == null || idEjecutivoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un ID de ejecutivo válido.");
            return;
        }
        
        // Convertir el idEjecutivo a entero
        int idEjecutivo = Integer.parseInt(idEjecutivoStr);

        // Crear el documento PDF
        Document documento = new Document();
        // Ruta personalizada para guardar el PDF
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporteContratosEjecutivo_" + idEjecutivo + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n Reporte de Contratos por Ejecutivo\n\n");

            documento.open();

            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 2 columnas para el reporte
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("Nombre Ejecutivo");
            tabla.addCell("Total Contratos");

            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaContratos(tabla, idEjecutivo)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporteContratosEjecutivo_" + idEjecutivo + ".pdf");
            if (archivoPDF.exists()) {
                // Abre el archivo PDF con la aplicación predeterminada
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para llenar la tabla con los datos de la base de datos
    private boolean llenarTablaContratos(PdfPTable tabla, int idEjecutivo) {
        boolean exito = false;
        String query = "SELECT e.nombre, COUNT(c.idContrato) AS totalContratos "
                     + "FROM Ejecutivo e "
                     + "JOIN Contrato c ON e.idEjecutivo = c.idEjecutivo "
                     + "WHERE e.idEjecutivo = ? "
                     + "GROUP BY e.nombre";

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query)) {
            
            pst.setInt(1, idEjecutivo); // Establecer el idEjecutivo en la consulta
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Añadir los datos a la tabla del reporte
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(rs.getString("totalContratos"));
                    exito = true; // Si llega aquí, el proceso fue exitoso
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    
    
    
    
    
    
    //                  afiliados y mascotas 
    
    @FXML
    void btnAfiliadosMascotasAction() { 
        // Crear el documento PDF
        Document documento = new Document();
        // Ruta personalizada para guardar el PDF
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporteAfiliadosYMascotas.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            // Imagen de encabezado
            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            // Título del reporte
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n Reporte de Afiliados y sus Mascotas \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 3 columnas
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Nombre Afiliado");
            tabla.addCell("Nombre Mascota");
            tabla.addCell("Especie");

            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaAfiliadosYMascotas(tabla)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporteAfiliadosYMascotas.pdf");
            if (archivoPDF.exists()) {
                // Abre el archivo PDF con la aplicación predeterminada
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para llenar la tabla con datos de la base de datos
    private boolean llenarTablaAfiliadosYMascotas(PdfPTable tabla) {
        boolean exito = false;
        // Consulta SQL corregida
        String query = "SELECT "
                + "CONCAT(p.primerNombre, ' ', p.segundoNombre, ' ', p.primerApellido, ' ', p.segundoApellido) AS nombreCompleto, "
                + "m.nombre AS mascotaNombre, "
                + "e.nombre AS especieNombre "
                + "FROM persona p "
                + "JOIN mascota m ON p.cedula = m.cedula_persona "
                + "JOIN especie e ON m.especie_codigo = e.codigo;";

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Extraer los datos de la consulta
                String nombreAfiliado = rs.getString("nombreCompleto"); // Alias 'nombreCompleto'
                tabla.addCell(nombreAfiliado);
                tabla.addCell(rs.getString("mascotaNombre"));
                tabla.addCell(rs.getString("especieNombre"));  // Usar el alias 'especieNombre'
            }
            exito = true; // Si llega aquí, el proceso fue exitoso

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

 
    
    
    
    
    
    
    
 // Método para generar el reporte de afiliados con mascotas mayores de 5 años
    @FXML
    void btnAfiliadosMascotasMayores5Action() { 
        // Crear el documento PDF
        Document documento = new Document();
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporteAfiliadosMascotasMayores5.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            // Imagen de encabezado
            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            // Título del reporte
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n Reporte de Afiliados con Mascotas Mayores de 5 Años \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 3 columnas
            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Nombre Afiliado");
            tabla.addCell("Nombre Mascota");
            tabla.addCell("Especie");

            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaAfiliadosMascotasMayores5(tabla)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporteAfiliadosMascotasMayores5.pdf");
            if (archivoPDF.exists()) {
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para llenar la tabla con datos de la base de datos
    private boolean llenarTablaAfiliadosMascotasMayores5(PdfPTable tabla) {
        boolean exito = false;
        // Consulta SQL para obtener afiliados con mascotas mayores de 5 años
        String query = "SELECT "
                + "CONCAT(p.primerNombre, ' ', p.segundoNombre, ' ', p.primerApellido, ' ', p.segundoApellido) AS nombreCompleto, "
                + "m.nombre AS mascotaNombre, "
                + "e.nombre AS especieNombre "
                + "FROM persona p "
                + "JOIN mascota m ON p.cedula = m.cedula_persona "
                + "JOIN especie e ON m.especie_codigo = e.codigo "
                + "WHERE TIMESTAMPDIFF(YEAR, m.fechaNacimiento, CURDATE()) > 5;";  // Condición para mascotas mayores de 5 años

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Extraer los datos de la consulta
                String nombreAfiliado = rs.getString("nombreCompleto");
                tabla.addCell(nombreAfiliado);
                tabla.addCell(rs.getString("mascotaNombre"));
                tabla.addCell(rs.getString("especieNombre"));
            }
            exito = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    
    
    
    
    
    
    
    
    
    
    @FXML
    void btnTotalFacturasAfiliadoAction() {
        // Crear el documento PDF
        Document documento = new Document();
        // Ruta personalizada para guardar el PDF
        String ruta = Constantes.RUTA_EXPORTAR_PDF;

        try {
            // Definir el destino del archivo PDF
            String destino = ruta + "reporteTotalFacturasAfiliado.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            // Imagen de encabezado
            Image header = Image.getInstance("src/main/java/img/isapet.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            // Título del reporte
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("\n\n Reporte de Total de Facturas Emitidas por Afiliados \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear la tabla con 2 columnas
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("Nombre Afiliado");
            tabla.addCell("Total Facturas Emitidas");

            // Conectar a la base de datos y llenar la tabla
            if (llenarTablaTotalFacturas(tabla)) {
                documento.add(tabla);
                JOptionPane.showMessageDialog(null, "Reporte creado con éxito en: " + destino);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al generar el reporte.");
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            documento.close();
        }

        // Intentar abrir el archivo PDF automáticamente
        try {
            File archivoPDF = new File(ruta + "reporteTotalFacturasAfiliado.pdf");
            if (archivoPDF.exists()) {
                // Abre el archivo PDF con la aplicación predeterminada
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo PDF.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el documento PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para llenar la tabla con datos de la base de datos
    private boolean llenarTablaTotalFacturas(PdfPTable tabla) {
        boolean exito = false;
        // Consulta SQL para obtener el total de facturas emitidas por afiliado
        String query = "SELECT "
                + "CONCAT(p.primerNombre, ' ', p.primerApellido) AS nombreCompleto, "
                + "COUNT(f.codigo) AS totalFacturas "
                + "FROM persona p "
                + "JOIN contratoafiliacion ca ON p.cedula = ca.Afiliado_Usuario_cedula "
                + "JOIN factura f ON f.codigo = ca.Pago_codigo "
                + "GROUP BY nombreCompleto;";

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement pst = cn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Extraer los datos de la consulta
                tabla.addCell(rs.getString("nombreCompleto"));
                tabla.addCell(String.valueOf(rs.getInt("totalFacturas")));
            }
            exito = true; // Si llega aquí, el proceso fue exitoso

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    
    
    
    
    
    
    
    
    
    
    @FXML
    void btnAtras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Principal.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnAtras.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Gestionar Reportes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesaria
    }
}
