package co.edu.uniquindio.pr3.subastasUQ.model;

import java.util.List;

public class SubastasQuindio {

    //Atributos de la clase
    private String nombre;
    private String direccion;
    private List<Producto> listaProductos;
    private List<Anuncio> listaAnuncios;
    private List<Usuario> listaUsuarios;

    //Constructores de la clase
    public SubastasQuindio() {
    }

    public SubastasQuindio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    //Getters y setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
