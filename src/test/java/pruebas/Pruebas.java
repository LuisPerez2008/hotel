/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import com.hotel.controlador.DetalleDao;
import com.hotel.controlador.DetalleDaoImpl;
import com.hotel.controlador.ReservaDao;
import com.hotel.controlador.ReservaDaoImpl;
import com.hotel.modelo.Detalle;
import com.hotel.modelo.Reserva;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author 51901
 */
public class Pruebas {

    
    public static void main(String[] args) {
        ReservaDao reservaDao = new ReservaDaoImpl();
        Reserva reser = reservaDao.buscarPorId(6);
        DetalleDao detalleDao = new DetalleDaoImpl();
        Detalle det = detalleDao.buscarPorId(6);
        
        
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    // Convertir el String a java.util.Date
                    Date fechaReserva = sdf.parse("2024-07-10");
                    Date fechaSalida = sdf.parse("2024-07-18");
                    Reserva reserva = new Reserva();
                    reserva.setId_usuario(reser.getId_usuario());
                    reserva.setId_huesped(reser.getId_huesped());
                    reserva.setFecha_reserva(fechaReserva);
                    reserva.setFecha_salida(fechaSalida);
                    reservaDao.actualizar(reserva);

                } catch (ParseException e) {
                    System.err.println("Error al convertir la fecha: " + e.getMessage());
                }
        
        /*if (pregunta == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    // Convertir el String a java.util.Date
                    Date fechaReserva = sdf.parse("2024-07-10");
                    Date fechaSalida = sdf.parse("2024-07-18");
                    Reserva reserva = new Reserva();
                    reserva.setId_usuario(reser.getId_usuario());
                    reserva.setId_huesped(reser.getId_huesped());
                    reserva.setFecha_reserva(fechaReserva);
                    reserva.setFecha_salida(fechaSalida);
                    reservaDao.actualizar(reserva);

                } catch (ParseException e) {
                    System.err.println("Error al convertir la fecha: " + e.getMessage());
                }
                //Iniciar Detalle
                int id_habitacion = det.getId_habitacion();
                int id_reserva = det.getId_reserva();
                int id_metodopago = 2;
                double precio_Total = 80;
                Detalle detalle = new Detalle();
                detalle.setId_habitacion(id_habitacion);
                detalle.setId_reserva(id_reserva);
                detalle.setId_metodoPago(id_metodopago);
                detalle.setPrecioTotal(precio_Total);
                detalleDao.actualizar(detalle);
                JOptionPane.showMessageDialog(null, "Actualizado");
                
            }
        
        /*
        List<HashMap<String, Object>> detalles = detalleDao.listarDetalle();
        
         for (HashMap<String, Object> detalle : detalles) {
            for (Map.Entry<String, Object> en : detalle.entrySet()) {
                String key = en.getKey();
                Object val = en.getValue();
                System.out.println(key + ": " + val);
            }
            System.out.println("---------");
        }*/
    }
    
}
