package co.edu.uniquindio.pr3.subastasUQ.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos de la clase
    private String codigo;
    private String fechaInicio;
    private String fechaFinal;
    private String nombreAnunciante;
    private Producto producto;
    private Compra compra;
    private List<Puja> listaPujas;

    //Metodos contructor de la clase
    public Anuncio(){

    }

    public Anuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto, Compra compra) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.nombreAnunciante = nombreAnunciante;
        this.producto = producto;
        this.compra = compra;
        this.listaPujas = new ArrayList<Puja>();
    }

    //getters() & setters()

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    //toString()

    @Override
    public String toString() {
        return "Anuncio{" +
                "codigo='" + codigo + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinal='" + fechaFinal + '\'' +
                ", nombreAnunciante='" + nombreAnunciante + '\'' +
                ", producto=" + producto.getCodigo() +
                ", compra=" + compra +
                '}';
    }
}
