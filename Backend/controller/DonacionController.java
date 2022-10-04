package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Donacion;
import connection.DBConnection;

public class DonacionController implements IDonacionController {

    @Override
    public String listarDonaciones(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "SELECT p.id_producto, p.nombre, p.categoria, p.perecedero, d.fecha_donacion FROM producto p "
                + "INNER JOIN donacion d ON (p.id_producto = d.id_producto) INNER JOIN usuario u ON (d.username = u.username) "
                + "WHERE d.username = '" + username + "'";
        
        
        List<String> donaciones = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                boolean perecedero = rs.getBoolean("perecedero");
                Date fecha_donacion = rs.getDate("fecha_donacion");

                Donacion donacion = new Donacion(id_producto, nombre, fecha_donacion,perecedero, categoria);

                donaciones.add(gson.toJson(donacion));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(donaciones);
    }
}
