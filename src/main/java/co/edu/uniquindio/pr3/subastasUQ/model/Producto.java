package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;

public class Producto {

    //Atributos de la clase
    private String codigo;
    private String nombre;
    private String descripcion;
    private String direccionImagen;
    private Double valorInicial;
    private TipoProducto tipoProducto;
    private boolean isAnunciado;

    //Metodos constructor de la clase
    public Producto(){

    }
    public Producto(String codigo, String nombre, String descripcion, String direccionImagen, Double valorInicial, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccionImagen = direccionImagen;
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
    public String getDireccionImagen() {
        return direccionImagen;
    }
    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
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
                ", direccionImagen='" + direccionImagen + '\'' +
                ", valorInicial=" + valorInicial +
                ", tipoProducto=" + tipoProducto +
                ", isAnunciado=" + isAnunciado +
                '}';
    }

    //-------------------------------------------------------------------------------------------------------------------------------
    //Metodos propios de la clase

    public void anunciarProducto(){
        setAnunciado(true);
    }

    public void desAnunciarProducto(){
        setAnunciado(false);
    }
}
