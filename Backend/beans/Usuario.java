package beans;

public class Usuario {

    private String username;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String direccion;
    private double cant_donada;
    private boolean tipo_usuario;

    public Usuario(String username, String contrasena, String nombre, String apellidos, String telefono, String email, String direccion, double cant_donada, boolean tipo_usuario) {
        this.username = username;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.cant_donada = cant_donada;
        this.tipo_usuario = tipo_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCant_donada() {
        return cant_donada;
    }

    public void setCant_donada(double cant_donada) {
        this.cant_donada = cant_donada;
    }

    public boolean isTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(boolean tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", contrasena=" + contrasena + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", cant_donada=" + cant_donada + ", tipo_usuario=" + tipo_usuario + '}';
    }



}
