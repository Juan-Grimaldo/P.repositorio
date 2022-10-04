package beans;

import java.sql.Date;

public class Producto {

    private int id_producto;
    private String nombre;
    private String categoria;
    private double cant;
    private Date fecha_vencimiento;
    private boolean perecedero;//Se cambia TINYINT a boolean para que no genere error, NB no recibe TINYINT

    public Producto(int id_producto, String nombre, String categoria, double cant, Date fecha_vencimiento, boolean perecedero) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cant = cant;
        this.fecha_vencimiento = fecha_vencimiento;
        this.perecedero = perecedero;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getCant() {
        return cant;
    }

    public void setCant(double cant) {
        this.cant = cant;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public boolean isPerecedero() {
        return perecedero;
    }

    public void setPerecedero(boolean perecedero) {
        this.perecedero = perecedero;
    }

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", categoria=" + categoria + ", cant=" + cant + ", fecha_vencimiento=" + fecha_vencimiento + ", perecedero=" + perecedero + '}';
    }

    
}
