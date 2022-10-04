package test;

import beans.Producto;
import com.mysql.jdbc.ResultSetRow;
import connection.DBConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author stilpz
 */
public class OperacionesDB {

    public static void main(String[] args) {
//        actualizarProducto(5, "Cambio de categoria");
        listarProducto();
    }

    public static void actualizarProducto(int id_producto, String categoria) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE producto SET categoria ='" + categoria + "' WHERE id_producto = " + id_producto;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public static void listarProducto() {
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM producto";

        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                double cantidad = rs.getDouble("cant");
                Date fecha = rs.getDate("fecha_vencimiento");
                boolean perecedero = rs.getBoolean("perecedero");

                Producto producto = new Producto(id, nombre, categoria, cantidad, fecha, perecedero);
                System.out.println(producto.toString());

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }
}
