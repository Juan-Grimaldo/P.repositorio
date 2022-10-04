package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String username, String contrasena);

    public String register(String username, String contrasena,
            String nombre, String apellidos, String telefono, String email,
            String direccion, double cant_donada, boolean tipo_usuario);

    public String pedir(String username);

    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellidos, String nuevoTelefono,
            String nuevoEmail, String nuevaDireccion, double nuevaCantDonada, boolean nuevoTipoUsuario);

    public String verCantidad(String username);

    public String devolverProductos(String username, Map<Integer, Integer> cant_productos);

    public String eliminar(String username);

    public String sumarKGDonados(String username, double nuevoSaldo);

}
