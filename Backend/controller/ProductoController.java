package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.sql.Date;

import com.google.gson.Gson;
import beans.Producto;
import connection.DBConnection;

public class ProductoController implements IProductoController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM producto";

        if (ordenar == true) {
            sql += " ORDER BY categoria " + orden;
        }

        List<String> productos = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                double cant = rs.getDouble("cant");
                Date fecha_vencimiento = rs.getDate("fecha_vencimiento");
                boolean perecedero = rs.getBoolean("perecedero");

                Producto producto = new Producto(id_producto, nombre, categoria, cant, (java.sql.Date) fecha_vencimiento, perecedero);

                productos.add(gson.toJson(producto));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(productos);

    }

    @Override
    public String devolver(int id_producto, String username) {

        DBConnection con = new DBConnection();
        String sql = "DELETE FROM donacion WHERE id_producto= " + id_producto + " and username = '"
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id_producto);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id_producto) {

        DBConnection con = new DBConnection();

        String sql = "UPDATE producto SET cant = (SELECT cant FROM producto WHERE id_producto = "
                + id_producto + ") + 5 WHERE id_producto = " + id_producto;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String donar(int id_producto, String username) {

        Timestamp fecha_donacion = new Timestamp(new Date().getTime());
//        double cant_producto = cant_producto;
        DBConnection con = new DBConnection();
        String sql = "Insert into donacion values ('" + id_producto + "', '" + username + "', '" + fecha_donacion + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id_producto);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id_producto) {

        DBConnection con = new DBConnection();
        String sql = "Update producto set cant = (cant + 5) where id_producto = " + id_producto;
        

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
}
