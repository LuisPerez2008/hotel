
package com.hotel.recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static final String url = "jdbc:mysql://localhost:3307/db_hotel?serverTimezone=UTC";
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String user = "root";
    public static final String pass = "1234567";

    private static Connection conn = null;

    private Conexion() {

    }

    public static Connection conectar() {

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, pass);

            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return conn;
    }

    public static void cerrar() {

        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

    }
}

