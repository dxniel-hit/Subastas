package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.IProductoControllerService;
import javafx.scene.image.Image;

import java.util.*;

public class Producto implements IProductoControllerService {

    //Atributos de la clase
    private String codigo;
    private String nombre;
    private String descripcion;
    private Image image;
    private Double valorInicial;
    private TipoProducto tipoProducto;
    private Boolean isAnunciado;

    //Metodos constructor de la clase
    public Producto() {

    }

    public Producto(String codigo, String nombre, String descripcion, Double valorInicial, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorInicial = valorInicial;
        this.tipoProducto = tipoProducto;
    }

    public Producto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.image = image;
        this.valorInicial = valorInicial;
        this.tipoProducto = tipoProducto;
        this.isAnunciado = false;
    }

    //getters() & setters()
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public boolean isAnunciado() {
        return isAnunciado;
    }

    public void setAnunciado(boolean anunciado) {
        isAnunciado = anunciado;
    }


    //toString()
    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + image + '\'' +
                ", valorInicial=" + valorInicial +
                ", tipoProducto=" + tipoProducto +
                ", isAnunciado=" + isAnunciado +
                '}';
    }

    //-------------------------------------------------------------------------------------------------------------------------------
    //Metodos propios de la clase

    public void anunciarProducto() {
        setAnunciado(true);
    }

    public void desAnunciarProducto() {
        setAnunciado(false);
    }


    @Override
    public List<ProductoDTO> obtenerProductos() {
        return null;
    }

    @Override
    public Producto crearProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        Producto p = new Producto(codigo,nombre,descripcion,image,valorInicial,tipoProducto);
        return p;
    }

    @Override
    public void imprimirProducto(String codigo) throws ProductoException {

    }

    @Override
    public boolean actualizarProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        return false;
    }

    @Override
    public boolean eliminarProducto(String codigo) throws ProductoException {
        return false;
    }
}
