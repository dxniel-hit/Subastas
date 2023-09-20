package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

public class Anunciante extends Usuario{

    //Atributos de la clase
    private List<Anuncio> listaAnuncios;
    private List<Producto> listaProductos;

    //Constructores
    public Anunciante(){

    }

    public Anunciante(String nombres, String apellidos, String identificacion, Integer edad, String usuario, String contrasenia, String email, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad, usuario, contrasenia, email, isAutenticado, tipoUsuario);
        this.listaAnuncios = new ArrayList<Anuncio>();
        this.listaProductos = new ArrayList<Producto>();
    }

    //getters & setters
    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    //toString()
    @Override
    public String toString() {
        return "Anunciante{" +
                "listaAnuncios=" + listaAnuncios +
                ", listaProductos=" + listaProductos +
                '}';
    }

    //hashCode() & equals()

}
