/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotel.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.IndexedColorMap;

public class ReporteExcel {

    public static void generateReport(DefaultTableModel model) {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Reservas");

        try {
            // Estilo para el título
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear un color personalizado con formato RGB
            ((XSSFCellStyle) tituloEstilo).setFillForegroundColor(new XSSFColor(new java.awt.Color(28, 56, 121), null));
            tituloEstilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setColor(IndexedColors.WHITE.getIndex());
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(0);
            Cell celdaTitulo = filaTitulo.createCell(0);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Reservas");

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 5));

            // Cabeceras de la tabla
            String[] cabecera = new String[]{"ID", "N° Habitacion", "Cliente", "Fecha de Reserva", "Fecha de Salida", "Precio Total"};

            CellStyle headerStyle = book.createCellStyle();
            ((XSSFCellStyle) headerStyle).setFillForegroundColor(new XSSFColor(new java.awt.Color(28, 56, 121), null));
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(2);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEncabezado = filaEncabezados.createCell(i);
                celdaEncabezado.setCellStyle(headerStyle);
                celdaEncabezado.setCellValue(cabecera[i]);
            }

            int numFilaDatos = 3;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderTop(BorderStyle.THIN);

            // Utiliza el DefaultTableModel proporcionado como parámetro
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                Row filaDatos = sheet.createRow(numFilaDatos);
                int numCol = 0;
                for (int columna = 0; columna < model.getColumnCount(); columna++) {
                    Cell celdaDatos = filaDatos.createCell(numCol++);
                    celdaDatos.setCellStyle(datosEstilo);
                    Object valorCelda = model.getValueAt(fila, columna);
                    if (valorCelda instanceof Number) {
                        celdaDatos.setCellValue(((Number) valorCelda).doubleValue());
                    } else {
                        celdaDatos.setCellValue(valorCelda != null ? valorCelda.toString() : "");
                    }
                }
                numFilaDatos++;
            }

            // Ajustar ancho de columnas
            for (int i = 0; i < cabecera.length; i++) {
                sheet.autoSizeColumn(i);
            }

            String fileName = "Reporte_Reservas";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();

            JOptionPane.showMessageDialog(null, "Reporte Generado");
            Desktop.getDesktop().open(file);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
