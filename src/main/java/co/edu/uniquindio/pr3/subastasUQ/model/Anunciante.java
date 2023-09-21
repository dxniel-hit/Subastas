package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.IAnunciante;

import java.util.ArrayList;
import java.util.List;

public class Anunciante extends Usuario implements IAnunciante {

    //Atributos de la clase
    private List<Anuncio> listaAnuncios;
    private List<Producto> listaProductos;

    //Constructores
    public Anunciante(){

    }

    public Anunciante(String nombres, String apellidos, String identificacion, Integer edad, SubastasQuindio subastasQuindio, String usuario, String contrasenia, String email, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad, subastasQuindio, usuario, contrasenia, email, isAutenticado, tipoUsuario);
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

    //------------------------------------------------------------------------------------------------------------------------------------
    //Metodos propios de la clase Anunciante

    //Metodo para obtener un Anuncio en la lista de Anuncios del Anunciante
    public Anuncio obtenerAnuncio(String codigo) {
        return getSubastasQuindio().obtenerAnuncio(codigo);
    }

    //Metodo para obtener un producto en la lista de productos del Anunciante
    public Producto obtenerProducto(String codigo) {
        Producto producto = null;
        try {
            producto = listaProductos.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().get();
        }
        catch(Exception ignored) {

        }
        return producto;
    }

    @Override
    public boolean crearAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException {
        if(obtenerAnuncio(codigo)!=null) throw new AnuncioException("El Anuncio con codigo: "+codigo+"ya se encuentra creado");
        Producto p = obtenerProducto(codigoProducto);
        if(p==null) throw new ProductoException("El Producto con codigo: "+codigoProducto+" "+"No ha sido creado por el anunciante");
        Anuncio a = new Anuncio(codigo, fechaInicio, fechaFinal, nombreAnunciante, p, null);
        listaAnuncios.add(a);
        getSubastasQuindio().getListaAnuncios().add(a);
        return true;
    }

    @Override
    public void imprimirAnuncio(String codigo) throws AnuncioException {
        Anuncio a = obtenerAnuncio(codigo);
        if(a==null) throw new AnuncioException("El Anuncio No se encuentra creado");
        System.out.println("Detalles del Anuncio:");
        System.out.println("Codigo: " + a.getCodigo());
        System.out.println("Fecha Inicio: " + a.getFechaInicio());
        System.out.println("Fecha Final: " + a.getFechaFinal());
        System.out.println("Producto: " + a.getProducto().getCodigo());
    }

    @Override
    public boolean actualizarAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException {
        Anuncio a = obtenerAnuncio(codigo);
        if(a==null) throw new AnuncioException("El Anuncio No se encuentra creado");
        Producto p = obtenerProducto(codigoProducto);
        if(p==null) throw new ProductoException("El Producto con codigo: "+codigoProducto+" "+"No ha sido creado por el anunciante");
        if(p.isAnunciado() && !a.getProducto().getCodigo().equals(codigoProducto)) throw new ProductoException("El Producto con codigo: "+codigoProducto+" "+"ya se encuentra anunciado");

        a.setFechaInicio(fechaInicio); a.setFechaFinal(fechaFinal);
        a.setNombreAnunciante(nombreAnunciante); a.setProducto(p);
        return true;
    }

    public boolean eliminarAnuncio(String codigo) throws AnuncioException{
        Anuncio a = obtenerAnuncio(codigo);
        if(a==null) throw new AnuncioException("El Anuncio No se encuentra creado");
        getSubastasQuindio().getListaAnuncios().remove(a);
        listaAnuncios.remove(a);
        return true;
    }

    @Override
    public boolean crearProducto(String codigo, String nombre, String descripcion, String direccionImagen, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        Producto p = getSubastasQuindio().obtenerProducto(codigo);
        if(p!=null) throw new ProductoException("El Producto con codigo: "+codigo+" "+"ya se encuentra creado");
        Producto producto = new Producto(codigo, nombre, descripcion, direccionImagen, valorInicial, tipoProducto);
        listaProductos.add(producto);
        getSubastasQuindio().getListaProductos().add(producto);
        return true;
    }

    @Override
    public void imprimirProducto(String codigo) throws ProductoException {
        Producto p = getSubastasQuindio().obtenerProducto(codigo);
        if(p==null) throw new ProductoException("El Producto con codigo: "+codigo+" no se encuentra creado");
        System.out.println("Detalles del Producto:");
        System.out.println("Codigo: " + p.getCodigo());
        System.out.println("Nombre Producto: " + p.getNombre());
        System.out.println("Descripción: " + p.getDescripcion());
        System.out.println("Valor Inicial: " + p.getValorInicial());
        System.out.println("El Producto esta anunciado: " + p.isAnunciado());
    }

    @Override
    public boolean actualizarProducto(String codigo, String nombre, String descripcion, String direccionImagen, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        Producto p = obtenerProducto(codigo);
        if(p==null) throw new ProductoException("El Producto con codigo: "+codigo+" "+"No ha sido creado por el anunciante");
        p.setNombre(nombre); p.setDescripcion(descripcion);
        p.setDireccionImagen(direccionImagen); p.setValorInicial(valorInicial);
        p.setTipoProducto(tipoProducto);
        return true;
    }

    @Override
    public boolean eliminarProducto(String codigo) throws ProductoException {
        Producto p = getSubastasQuindio().obtenerProducto(codigo);
        if(p==null) throw new ProductoException("El Producto No se encuentra creado");
        getSubastasQuindio().getListaProductos().remove(p);
        listaProductos.remove(p);
        return true;
    }

}
