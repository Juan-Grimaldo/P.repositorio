package beans;

import java.sql.Date;

public class Donacion {

    private int id_producto;
    private String username;
    private Date fecha_donacion;

    private boolean perecedero;
    private String categoria;

    public Donacion(int id_producto, String username, Date fecha_donacion, boolean perecedero, String categoria) {
        this.id_producto = id_producto;
        this.username = username;
        this.fecha_donacion = fecha_donacion;
        this.perecedero = perecedero;
        this.categoria = categoria;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha_donacion() {
        return fecha_donacion;
    }

    public void setFecha_donacion(Date fecha_donacion) {
        this.fecha_donacion = fecha_donacion;
    }

    public boolean isPerecedero() {
        return perecedero;
    }

    public void setPerecedero(boolean perecedero) {
        this.perecedero = perecedero;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Donacion{" + "id_producto=" + id_producto + ", username=" + username + ", fecha_donacion=" + fecha_donacion + ", perecedero=" + perecedero + ", categoria=" + categoria + '}';
    }

}
