package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Comprador extends Usuario{

    //Atributos de la clase
    private List<Puja> listaPujas;
    private List<Integer> cantidadDeVecesPujada;
    private List<Compra> listaCompras;

    //Constructores
    public Comprador(){

    }

    public Comprador(String nombres, String apellidos, String identificacion, Integer edad, String usuario, String contrasenia, String email, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad, usuario, contrasenia, email, isAutenticado, tipoUsuario);
        this.listaPujas = new ArrayList<Puja>();
        this.cantidadDeVecesPujada = new ArrayList<Integer>();
        this.listaCompras = new ArrayList<Compra>();
    }

    //getters() & setters()
    public List<Puja> getListaPujas() {
        return listaPujas;
    }
    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }
    public List<Integer> getCantidadDeVecesPujada() {
        return cantidadDeVecesPujada;
    }
    public void setCantidadDeVecesPujada(List<Integer> cantidadDeVecesPujada) {
        this.cantidadDeVecesPujada = cantidadDeVecesPujada;
    }
    public List<Compra> getListaCompras() {
        return listaCompras;
    }
    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    //toString()
    @Override
    public String toString() {
        return "Comprador{" +
                "listaPujas=" + listaPujas +
                ", cantidadDeVecesPujada=" + cantidadDeVecesPujada +
                ", listaCompras=" + listaCompras +
                '}';
    }

    //Metodos propios de la clase Comprador
    public int devolverNumeroPujasEnAnuncio(String codigoAnuncio){
        return (int) listaPujas.stream().filter(p -> p.getAnuncio().getCodigo().equals(codigoAnuncio)).count();
    }
}
