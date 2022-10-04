package controller;

import beans.Usuario;
import com.google.gson.Gson;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements IUsuarioController {

    @Override
    public String login(String username, String contrasena) {
        Gson gson = new Gson();
        DBConnection con = new DBConnection();

        String sql = "SELECT * FROM usuario WHERE username = '" + username + "' AND contrasena = '" + contrasena + "'";

        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                double cant_donada = rs.getDouble("cant_donada");
                boolean tipo_usuario = rs.getBoolean("tipo_usuario");

                Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, telefono, email, direccion, cant_donada, tipo_usuario);
                return gson.toJson(usuario);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String register(String username, String contrasena, String nombre,
            String apellidos, String telefono, String email, String direccion,
            double cant_donada, boolean tipo_usuario) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "INSERT INTO usuario VALUES('" + username + "', '" + contrasena + "', '" + nombre
                + "', '" + apellidos + "', '" + telefono + "', '" + email
                + "','" + direccion + "', " + cant_donada + ", " + tipo_usuario + ")";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, telefono, email, direccion, cant_donada, tipo_usuario);

            st.close();

            return gson.toJson(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String pedir(String username) {
        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from usuario where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                double cant_donada = rs.getDouble("cant_donada");
                boolean tipo_usuario = rs.getBoolean("tipo_usuario");

                Usuario usuario = new Usuario(username, contrasena,
                        nombre, apellidos, telefono, email, direccion,
                        cant_donada, tipo_usuario);

                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellidos, String nuevoTelefono,
            String nuevoEmail, String nuevaDireccion, double nuevaCantDonada, boolean nuevoTipoUsuario) {

        DBConnection con = new DBConnection();

        String sql = "UPDATE usuario SET contrasena = '" + nuevaContrasena
                + "', nombre = '" + nuevoNombre + "', "
                + "apellidos = '" + nuevosApellidos + "', telefono = '" + nuevoTelefono + "', email = '"
                + nuevoEmail + "', direccion = '" + nuevaDireccion + "', cant_donada = " + nuevaCantDonada + ", tipo_usuario = ";

        if (nuevoTipoUsuario == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }

        sql += " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    @Override
    public String verCantidad(String username) {

        DBConnection con = new DBConnection();
        String sql = "Select id_producto, 5 as num_kg_prod from donacion where username = '"
                + username + "' group by id_producto;";

        Map<Integer, Integer> cant = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                int num_kg_prod = rs.getInt("num_kg_prod");

                cant.put(id_producto, num_kg_prod);
            }

            devolverProductos(username, cant);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String devolverProductos(String username, Map<Integer, Integer> cant) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> pelicula : cant.entrySet()) {
                int id_producto = pelicula.getKey();
                int num_kg_prod = pelicula.getValue();

                String sql = "Update producto set cant = (Select cant - " + num_kg_prod
                        + " from producto where id_producto = " + id_producto + ") where id_producto = " + id_producto;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String eliminar(String username) {

        DBConnection con = new DBConnection();

        String sql1 = "DELETE FROM donacion where username = '" + username + "'";
        String sql2 = "DELETE FROM usuario where username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarKGDonados(String username, double nuevoSaldo) {

        DBConnection con = new DBConnection();
        String sql = "Update usuario set cant_donada = " + nuevoSaldo + " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
}
