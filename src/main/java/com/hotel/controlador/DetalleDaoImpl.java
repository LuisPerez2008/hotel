package com.hotel.controlador;

import com.hotel.modelo.Detalle;
import com.hotel.recursos.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.filechooser.FileSystemView;

public class DetalleDaoImpl implements DetalleDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Detalle> listar() {
        List<Detalle> detalles = new ArrayList();
        String sql = "SELECT * FROM detalle WHERE esatdo = '1'";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setId(rs.getInt("id"));
                detalle.setId_habitacion(rs.getInt("id_habitacion"));
                detalle.setId_reserva(rs.getInt("id_reserva"));
                detalle.setId_metodoPago(rs.getInt("id_metodoPago"));
                detalle.setPrecioTotal(rs.getDouble("precioTotal"));
                detalles.add(detalle);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return detalles;
    }

    @Override
    public void registrar(Detalle detalle) {
        String sql = "INSERT INTO detalle (id_habitacion, id_reserva, id_metodoPago, precio_total) VALUES (?, ?, ?, ?)";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, detalle.getId_habitacion());
            ps.setInt(2, detalle.getId_reserva());
            ps.setInt(3, detalle.getId_metodoPago());
            ps.setDouble(4, detalle.getPrecioTotal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void actualizar(Detalle detalle) {
        String sql = "UPDATE detalle SET id_habitacion = ?, id_reserva = ?, id_metodoPago = ?, precio_total = ? WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, detalle.getId_habitacion());
            ps.setInt(2, detalle.getId_reserva());
            ps.setInt(3, detalle.getId_metodoPago());
            ps.setDouble(4, detalle.getPrecioTotal());
            ps.setInt(5, detalle.getId());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void borrar(int id) {
        String sql = "UPDATE huesped SET estado = '2' WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public Detalle buscarPorId(int id) {
        Detalle detalle = null;
        String sql = "SELECT * FROM detalle WHERE id = ?";
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                detalle = new Detalle();
                detalle.setId(rs.getInt("id"));
                detalle.setId_habitacion(rs.getInt("id_habitacion"));
                detalle.setId_reserva(rs.getInt("id_reserva"));
                detalle.setId_metodoPago(rs.getInt("id_metodoPago"));
                detalle.setPrecioTotal(rs.getDouble("precio_total"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
        return detalle;
    }

    @Override
    public int buscarPoridHbaitacion(int idHabitacion) {
        int id = -1;
        String sql = "SELECT MAX(d.id) as id "
                + "FROM detalle d "
                + "JOIN reserva r ON d.id_reserva = r.id "
                + "JOIN habitacion h ON d.id_habitacion = h.id "
                + "WHERE h.id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idHabitacion);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return id;
    }

    @Override
    public List<HashMap<String, Object>> listarDetalle() {
        String sql = "SELECT d.id, hab.numero AS \"N° habitacion\", h.nombre AS Cliente, r.fecha_reserva, r.fecha_salida, d.precio_total "
                + "FROM detalle d "
                + "JOIN reserva r ON d.id_reserva = r.id "
                + "JOIN huesped h ON r.id_huesped = h.id "
                + "JOIN habitacion hab ON d.id_habitacion = hab.id ORDER BY d.id DESC";

        List<HashMap<String, Object>> lista = new ArrayList<>();
        try {
            conn = Conexion.conectar(); // Establecer la conexión
            ps = conn.prepareStatement(sql); // Preparar la consulta
            rs = ps.executeQuery(); // Ejecutar la consulta
            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<>();

                map.put("id", rs.getInt("id"));
                map.put("N° habitacion", rs.getString("N° habitacion"));
                map.put("Cliente", rs.getString("Cliente"));
                map.put("fecha_reserva", rs.getDate("fecha_reserva"));
                map.put("fecha_salida", rs.getDate("fecha_salida"));
                map.put("precio_total", rs.getBigDecimal("precio_total"));

                lista.add(map);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cerrar ResultSet
                }
                if (ps != null) {
                    ps.close(); // Cerrar PreparedStatement
                }
                if (conn != null) {
                    Conexion.cerrar(); // Cerrar conexión
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
        return lista;

    }

    @Override
    public void actualizarPrecio(int idDetalle, double precio) {
        String sql = "UPDATE detalle SET precio_total = ? WHERE id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, precio);
            ps.setInt(2, idDetalle);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }
    }

    @Override
    public void convertSaleToPDF(int idDetalle, String nomUsuario, String fechaRegistro, int idCliente, int idRegistro, double total, String tipo_pago) {
        try {
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File carpetaComprobantes = new File(url + File.separator + "Comprobantes");

            if (!carpetaComprobantes.exists()) {
                carpetaComprobantes.mkdir();
            }

            File salida = new File(carpetaComprobantes + File.separator + "Boleta" + idDetalle + ".pdf");

            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Vendedor: " + nomUsuario + "\nFolio: " + idDetalle + "\nFecha: " + fechaRegistro + "\n\n");

            // Encabezado de la empresa
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = {20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell("");
            Encabezado.addCell("");
            // Información del hotel
            String infoHotel = "SELECT * FROM hotel";
            try {
                conn = Conexion.conectar();
                ps = conn.prepareStatement(infoHotel);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Encabezado.addCell("Ruc:    " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n");
                } else {
                    System.out.println("No se encontraron datos del hotel");
                    Encabezado.addCell("No hay datos disponibles");
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener datos del hotel: " + e.toString());
                Encabezado.addCell("Error al obtener datos");
            } finally {
                Conexion.cerrar();
            }

            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            // Datos del cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DEL CLIENTE" + "\n\n");
            doc.add(cli);

            PdfPTable hoteles = new PdfPTable(3);
            hoteles.setWidthPercentage(100);
            hoteles.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = {50f, 25f, 25f};
            hoteles.setWidths(columnWidthsCliente);
            hoteles.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cliTel = new PdfPCell(new Phrase("Teléfono", negrita));
            PdfPCell cliDir = new PdfPCell(new Phrase("Email", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            cliTel.setBorder(Rectangle.NO_BORDER);
            cliDir.setBorder(Rectangle.NO_BORDER);

            hoteles.addCell(cliNom);
            hoteles.addCell(cliTel);
            hoteles.addCell(cliDir);

            String infoCliente = "SELECT * FROM huesped WHERE id = ?";
            try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(infoCliente)) {
                ps.setInt(1, idCliente);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        hoteles.addCell(rs.getString("nombre"));
                        hoteles.addCell(rs.getString("telefono"));
                        hoteles.addCell(rs.getString("correo") + "\n\n");
                    } else {
                        hoteles.addCell("Publico en General");
                        hoteles.addCell("S/N");
                        hoteles.addCell("S/N" + "\n\n");
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            } finally {
                Conexion.cerrar();
            }
            doc.add(hoteles);

            // Sección de Servicio
            Paragraph servicio = new Paragraph();
            servicio.add(Chunk.NEWLINE);
            servicio.add("DETALLES DEL SERVICIO" + "\n\n");
            doc.add(servicio);

            PdfPTable tablaServicio2 = new PdfPTable(5);
            tablaServicio2.setWidthPercentage(100);
            tablaServicio2.getDefaultCell().setBorder(0);
            float[] columnWidthsServicio = {20f, 20f, 20f, 20f, 20f};
            tablaServicio2.setWidths(columnWidthsServicio);
            tablaServicio2.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell f1 = new PdfPCell(new Phrase("N° Habitación", negrita));
            PdfPCell f2 = new PdfPCell(new Phrase("Fecha Entrada", negrita));
            PdfPCell f3 = new PdfPCell(new Phrase("Fecha Salida", negrita));
            PdfPCell f4 = new PdfPCell(new Phrase("Precio Habitación", negrita));
            PdfPCell f5 = new PdfPCell(new Phrase("Precio Final", negrita));

            f1.setBorder(Rectangle.NO_BORDER);
            f2.setBorder(Rectangle.NO_BORDER);
            f3.setBorder(Rectangle.NO_BORDER);
            f4.setBorder(Rectangle.NO_BORDER);
            f5.setBorder(Rectangle.NO_BORDER);

            f1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            f2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            f3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            f4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            f5.setBackgroundColor(BaseColor.LIGHT_GRAY);

            tablaServicio2.addCell(f1);
            tablaServicio2.addCell(f2);
            tablaServicio2.addCell(f3);
            tablaServicio2.addCell(f4);
            tablaServicio2.addCell(f5);

            String infoServicio = "SELECT h.numero, r.fecha_reserva, r.fecha_salida, h.precio, "
                    + "(DATEDIFF(r.fecha_salida, r.fecha_reserva) * h.precio) AS total_precio "
                    + "FROM detalle d "
                    + "JOIN habitacion h ON d.id_habitacion = h.id "
                    + "JOIN reserva r ON r.id = d.id_reserva "
                    + "WHERE d.id = ?";

            try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(infoServicio)) {

                ps.setInt(1, idDetalle); // Asegúrate de que idDetalle esté definido y contenga el valor correcto

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tablaServicio2.addCell(rs.getString("numero"));
                        tablaServicio2.addCell(rs.getString("fecha_reserva"));
                        tablaServicio2.addCell(rs.getString("fecha_salida"));
                        tablaServicio2.addCell(String.format("%.2f", rs.getDouble("precio")));
                        tablaServicio2.addCell(String.format("%.2f", rs.getDouble("total_precio")));
                    } else {
                        // Si no se encuentran datos, añadir celdas con "N/A"
                        for (int i = 0; i < 5; i++) {
                            tablaServicio2.addCell("N/A");
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error al obtener detalles del servicio: " + ex.toString());
                // Manejo de errores: agregar una fila con mensaje de error
                for (int i = 0; i < 5; i++) {
                    tablaServicio2.addCell("Error al cargar datos");
                }
            } finally {
                Conexion.cerrar();
            }

            doc.add(tablaServicio2);
            // Tabla de detalles

            Paragraph extras = new Paragraph();
            extras.add(Chunk.NEWLINE);
            extras.add("EXTRAS DEL SERVICIO" + "\n\n");
            doc.add(extras);
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = {10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell c1 = new PdfPCell(new Phrase("Cant.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("P. Unit.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("P. Total", negrita));

            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);

            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);

            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);

            String infoDetalleExtras = "SELECT e.cantidad, i.nombre, i.precio, (e.cantidad * i.precio) AS Total "
                    + "FROM extras e "
                    + "JOIN insumos i ON i.id = e.id_insumo "
                    + "JOIN reserva r ON r.id = e.id_reserva "
                    + "WHERE r.id = ?";

            try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(infoDetalleExtras)) {

                ps.setInt(1, idRegistro); // Asegúrate de que idRegistro esté definido y contenga el valor correcto

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        tabla.addCell(rs.getString("cantidad"));
                        tabla.addCell(rs.getString("nombre"));
                        tabla.addCell(String.format("%.2f", rs.getDouble("precio")));
                        tabla.addCell(String.format("%.2f", rs.getDouble("Total")));
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error al obtener detalles de extras: " + ex.toString());
                // Manejo de errores: agregar una fila con mensaje de error
                for (int i = 0; i < 4; i++) {
                    tabla.addCell("Error al cargar datos");
                }
            } finally {
                Conexion.cerrar();
            }

            doc.add(tabla);

            // Total
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            Paragraph Tipopago = new Paragraph();
            Tipopago.add(Chunk.NEWLINE);
            Tipopago.add("Tipo de Pago S/: " + tipo_pago);
            Tipopago.setAlignment(Element.ALIGN_LEFT);
            doc.add(Tipopago);

            // Firma
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        } finally {
            Conexion.cerrar();
        }

    }

    @Override
    public double GanaciaUltimoMes() {

        double totalPrecio = 0.0;
        String sql = "SELECT SUM(d.precio_total) AS total_precio "
                + "FROM detalle d "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "WHERE r.fecha_reserva >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalPrecio = rs.getDouble("total_precio");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return totalPrecio;
    }

    @Override
    public Map<String, Integer> reservasUltimos30Dias() {
        Map<String, Integer> reservasPorDia = new HashMap<>();
        String sql = "SELECT DATE(NOW() - INTERVAL n.n DAY) AS dia, "
                + "COALESCE(COUNT(r.fecha_reserva), 0) AS cantidad_reservas "
                + "FROM (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 "
                + "UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 "
                + "UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 "
                + "UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 "
                + "UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 "
                + "UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 "
                + "UNION ALL SELECT 24 UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 "
                + "UNION ALL SELECT 28 UNION ALL SELECT 29) n "
                + "LEFT JOIN reserva r ON DATE(r.fecha_reserva) = DATE(NOW() - INTERVAL n.n DAY) "
                + "WHERE DATE(NOW() - INTERVAL n.n DAY) >= CURDATE() - INTERVAL 30 DAY "
                + "GROUP BY dia "
                + "ORDER BY dia DESC";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String dia = rs.getString("dia");
                int cantidadReservas = rs.getInt("cantidad_reservas");
                reservasPorDia.put(dia, cantidadReservas);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return reservasPorDia;
    }

    @Override
    public Map<String, Integer> cantMetodoPago() {
        Map<String, Integer> metodoPagoMap = new HashMap<>();
        String sql = "SELECT m.tipopago AS nombre, COUNT(d.id) AS cantidad "
                + "FROM detalle d "
                + "JOIN habitacion h ON h.id = d.id_habitacion "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "JOIN metodo_pago m ON m.id = d.id_metodoPago "
                + "GROUP BY m.tipopago";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                metodoPagoMap.put(nombre, cantidad);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return metodoPagoMap;
    }

    @Override
    public double GanaciaProductoUltimoMes() {
        double totalPrecio = 0.0;
        String sql = "SELECT SUM(e.precio) AS total_precio "
                + "FROM extras e "
                + "JOIN reserva r ON r.id = e.id_reserva "
                + "WHERE r.fecha_reserva >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalPrecio = rs.getDouble("total_precio");
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return totalPrecio;
    }

    @Override
    public List<HashMap<String, Object>> listarDetalleReporte(int id) {
        List<HashMap<String, Object>> detalles = new ArrayList<>();
        String sql = "SELECT d.id AS 'Id Detalle', hab.numero AS 'N° Habitacion', h.nombre AS 'Cliente', "
                + "r.fecha_reserva AS 'Fecha de Ingreso', r.fecha_salida AS 'Fecha de Salida', "
                + "d.precio_total AS 'Precio Total' "
                + "FROM detalle d "
                + "JOIN habitacion hab ON d.id_habitacion = hab.id "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "JOIN huesped h ON h.id = r.id_huesped "
                + "JOIN usuario u ON u.id = r.id_usuario "
                + "WHERE d.id = ?";

        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el parámetro en la consulta
            rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> detalle = new HashMap<>();
                detalle.put("Id Detalle", rs.getInt("Id Detalle"));
                detalle.put("N° Habitacion", rs.getString("N° Habitacion"));
                detalle.put("Cliente", rs.getString("Cliente"));
                detalle.put("Fecha de Ingreso", rs.getDate("Fecha de Ingreso"));
                detalle.put("Fecha de Salida", rs.getDate("Fecha de Salida"));
                detalle.put("Precio Total", rs.getDouble("Precio Total"));
                detalles.add(detalle);
            }

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Conexion.cerrar();
        }

        return detalles;
    }

    @Override
    public List<HashMap<String, Object>> listarExtrasReporte(int id) {
        List<HashMap<String, Object>> extrasReporte = new ArrayList<>();
        String sql = "SELECT i.nombre AS Producto, e.cantidad AS cantidad, e.precio "
                + "FROM detalle d "
                + "JOIN reserva r ON r.id = d.id_reserva "
                + "JOIN extras e ON e.id_reserva = r.id "
                + "JOIN insumos i ON i.id = e.id_insumo "
                + "WHERE d.id = ?";

        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); // Establecer el parámetro en la consulta
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> extra = new HashMap<>();
                extra.put("Producto", rs.getString("Producto"));
                extra.put("cantidad", rs.getInt("cantidad"));
                extra.put("precio", rs.getDouble("precio"));
                extrasReporte.add(extra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrar();
        }

        return extrasReporte;
    }
}
