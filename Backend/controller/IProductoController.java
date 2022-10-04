package controller;

public interface IProductoController {

    public String listar(boolean ordenar, String orden);

    public String devolver(int id_producto, String username);

    public String sumarCantidad(int id_producto);
//
    public String donar(int id_producto, String username);

    public String modificar(int id_producto);

}
