package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.IAnunciante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Anunciante extends Usuario implements IAnunciante, Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos de la clase
    private List<Anuncio> listaAnuncios;
    private List<Producto> listaProductos;

    //Constructores
    public Anunciante() {

    }

    public Anunciante(String nombres, String apellidos, String identificacion, Integer edad, Subasta subasta, String usuario, String contrasenia, String email, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad, subasta, usuario, contrasenia, email, isAutenticado, tipoUsuario);
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
        } catch (Exception ignored) {

        }
        return producto;
    }

    @Override
    public boolean crearAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException {
        if (obtenerAnuncio(codigo) != null)
            throw new AnuncioException("El Anuncio con codigo: "+ codigo+" "+"ya se encuentra creado");
        Producto p = obtenerProducto(codigoProducto);
        if (p == null)
            throw new ProductoException("El Producto con codigo: " + codigoProducto + " " + "No ha sido creado por el anunciante");
        if (p.isAnunciado())
            throw new ProductoException("El Producto con codigo: " + codigoProducto + " " + "Ya se encuentra anunciado");
        Anuncio a = new Anuncio(codigo, fechaInicio, fechaFinal, nombreAnunciante, p, null);
        listaAnuncios.add(a);
        getSubastasQuindio().getListaAnuncios().add(a);
        p.setAnunciado(true);
        return true;
    }

    @Override
    public void imprimirAnuncio(String codigo) throws AnuncioException {
        Anuncio a = obtenerAnuncio(codigo);
        if (a == null) throw new AnuncioException("El Anuncio No se encuentra creado");
        System.out.println("Detalles del Anuncio:");
        System.out.println("Codigo: " + a.getCodigo());
        System.out.println("Fecha Inicio: " + a.getFechaInicio());
        System.out.println("Fecha Final: " + a.getFechaFinal());
        System.out.println("Producto: " + a.getProducto().getCodigo());
    }

    @Override
    public boolean actualizarAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException {
        Anuncio a = obtenerAnuncio(codigo);
        if (a == null) throw new AnuncioException("El Anuncio No se encuentra creado");
        Producto p = obtenerProducto(codigoProducto);
        if (p == null)
            throw new ProductoException("El Producto con codigo: " + codigoProducto + " " + "No ha sido creado por el anunciante");
        if (p.isAnunciado() && !a.getProducto().getCodigo().equals(codigoProducto))
            throw new ProductoException("El Producto con codigo: " + codigoProducto + " " + "ya se encuentra anunciado");

        //Se verifica el anuncio no tenga pujas asociadas
        if (a.getListaPujas().size()>0)
            throw new ProductoException("El anuncio no puede ser modificado, ya que tiene pujas asociadas");

        //Se intenta obtener la compra en el anuncio
        Compra compraAnuncio = null;
        try{
            compraAnuncio = a.getCompra();
        }catch (Exception ignored){

        }
        //Si el anuncio tiene una compra no se puede modificar
        if(compraAnuncio != null) throw new AnuncioException("El anuncio no puede ser modificado, ya que tiene una compra asociada");

        a.setFechaInicio(fechaInicio);
        a.setFechaFinal(fechaFinal);
        a.setNombreAnunciante(nombreAnunciante);
        a.getProducto().setAnunciado(false);
        a.setProducto(p);
        p.setAnunciado(true);
        return true;
    }

    @Override
    public boolean eliminarAnuncio(String codigo) throws AnuncioException {
        Anuncio a = obtenerAnuncio(codigo);
        if (a == null) throw new AnuncioException("El Anuncio No se encuentra creado");
        //Se intenta obtener la compra en el anuncio
        Compra compraAnuncio = null;
        try{
            compraAnuncio = a.getCompra();
        }catch (Exception ignored){

        }
        //Si el anuncio tiene una compra no se puede modificar
        if(compraAnuncio != null) throw new AnuncioException("El anuncio no puede ser eliminado, ya que tiene una compra asociada");

        a.getListaPujas().forEach(p ->{
            p.getComprador().getListaPujas().remove(p); //Se elimina la puja de la listaPujas del Comprador
            getSubastasQuindio().getListaPujas().remove(p);  //Se elimina la puja de la listaPujas de la clase global Subasta
        }); //Se eliminan las pujas en la listaPujas de Comprador
        getSubastasQuindio().getListaAnuncios().remove(a);
        listaAnuncios.remove(a);
        a.getProducto().setAnunciado(false);
        return true;
    }

    @Override
    public boolean crearProducto(String codigo, String nombre, String descripcion, String image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        Producto p = getSubastasQuindio().obtenerProducto(codigo);
        if (p != null)
            throw new ProductoException("El Producto con codigo: " + codigo + " " + "ya se encuentra creado");
        Producto producto = new Producto(codigo, nombre, descripcion, image, valorInicial, tipoProducto);
        listaProductos.add(producto);
        getSubastasQuindio().getListaProductos().add(producto);
        return true;
    }

    @Override
    public void imprimirProducto(String codigo) throws ProductoException {
        Producto p = getSubastasQuindio().obtenerProducto(codigo);
        if (p == null) throw new ProductoException("El Producto con codigo: " + codigo + " no se encuentra creado");
        System.out.println("Detalles del Producto:");
        System.out.println("Codigo: " + p.getCodigo());
        System.out.println("Nombre Producto: " + p.getNombre());
        System.out.println("Descripción: " + p.getDescripcion());
        System.out.println("Valor Inicial: " + p.getValorInicial());
        System.out.println("El Producto esta anunciado: " + p.isAnunciado());
    }

    @Override
    public boolean actualizarProducto(String codigo, Producto producto) throws ProductoException {
        Producto productoActualizado = obtenerProducto(codigo);
        if (productoActualizado == null) {
            throw new ProductoException("El Producto con codigo: " + codigo + " " + "No ha sido creado por el anunciante");
        } else {
            if(productoActualizado.isAnunciado()) {
                throw new ProductoException("El producto se encuentra anunciado por lo que no puede ser actualizado");
            }else{
                productoActualizado.setNombre(producto.getNombre());
                productoActualizado.setDescripcion(producto.getDescripcion());
                productoActualizado.setImage(producto.getImage());
                productoActualizado.setValorInicial(producto.getValorInicial());
                productoActualizado.setTipoProducto(producto.getTipoProducto());
                return true;
            }
        }
    }

    @Override
    public boolean eliminarProducto(String codigo) throws ProductoException {
        Producto p = getSubastasQuindio().obtenerProducto(codigo);
        if (p == null) throw new ProductoException("El Producto No se encuentra creado");
        if(p.isAnunciado()) throw new ProductoException("No se puede eliminar el producto ya que se encuentra anunciado");
        getSubastasQuindio().getListaProductos().remove(p);
        listaProductos.remove(p);
        return true;
    }

    //Metodo para realizar la compra de un anuncio
    public Compra realizarVenta(String codigoAnuncio, String codigoProducto, Double valor, String fecha, String identificacionComprador, Anunciante anunciante) throws AnuncianteException {
        //Se intenta obtener la compra en el anuncio
        Anuncio a = obtenerAnuncio(codigoAnuncio);
        Compra compraAnuncio = null;
        try{
            compraAnuncio = a.getCompra();
        }catch (Exception ignored){

        }

        //Si el anuncio tiene una compra asociada no se puede vender nuevamente
        if(compraAnuncio!=null)  throw new AnuncianteException("El anuncio ya tiene una venta asociada");

        //Se realiza la compra
        Comprador comprador = getSubastasQuindio().obtenerComprador(identificacionComprador);
        Compra c = new Compra(obtenerProducto(codigoProducto), valor, fecha, comprador, anunciante);
        getSubastasQuindio().getListaCompras().add(c);
        comprador.getListaCompras().add(c);
        a.setCompra(c);

        return c;
    }

}
