package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.ISubasta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class Subasta implements ISubasta, Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos de la clase
    private String nombre;
    private String direccion;
    private List<Producto> listaProductos;
    private List<Anuncio> listaAnuncios;
    private List<Usuario> listaUsuarios;
    private List<Puja> listaPujas;
    private List<Compra> listaCompras;

    //Constructores de la clase
    public Subasta() {
        this.listaProductos = new ArrayList<Producto>();
        this.listaAnuncios = new ArrayList<Anuncio>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.listaPujas = new ArrayList<Puja>();
        this.listaCompras = new ArrayList<Compra>();
    }

    public Subasta(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaProductos = new ArrayList<Producto>();
        this.listaAnuncios = new ArrayList<Anuncio>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.listaPujas = new ArrayList<Puja>();
        this.listaCompras = new ArrayList<Compra>();
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

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos de la Clase SubastasQuindio

    //Metodo para encontrar la posicion de un usuario en la lista de Usuarios de SubastasQuindio
    public int encontrarPosUsuario(String usuario) {
        return IntStream.range(0, listaUsuarios.size())
                .filter(i -> listaUsuarios.get(i).getUsuario().equals(usuario))
                .findFirst()
                .orElse(-1);
    }

    //Metodo para obtener un Anunciante en la lista de Usuarios de SubastasQuindio
    public Anunciante obtenerAnunciante(String identificacion) {
        Anunciante a = null;
        try {
            a = (Anunciante) listaUsuarios.stream().filter(u -> u.getIdentificacion().equals(identificacion) && u.getTipoUsuario().equals(TipoUsuario.ANUNCIANTE)).findFirst().get();
        } catch (Exception ignored) {

        }
        return a;
    }

    //Metodo para obtener un Comprador en la lista de Usuarios de SubastasQuindio
    public Comprador obtenerComprador(String identificacion) {
        Comprador c = null;
        try {
            c = (Comprador) listaUsuarios.stream().filter(u -> u.getIdentificacion().equals(identificacion) && u.getTipoUsuario().equals(TipoUsuario.COMPRADOR)).findFirst().get();
        } catch (Exception ignored) {

        }
        return c;
    }

    //Metodo para obtener un producto en la lista de productos de SubastasQuindio
    public Producto obtenerProducto(String codigo) {
        Producto producto = null;
        try {
            producto = listaProductos.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().get();
        } catch (Exception ignored) {

        }
        return producto;
    }

    //Metodo para obtener un Anuncio en la lista de anuncios de SubastasQuindio
    public Anuncio obtenerAnuncio(String codigo) {
        Anuncio anuncio = null;
        try {
            anuncio = listaAnuncios.stream().filter(a -> a.getCodigo().equals(codigo)).findFirst().get();
        } catch (Exception ignored) {

        }
        return anuncio;
    }

    //Metodo para verificar si un usuario esta en uso
    public boolean verificarUsuario(String usuario) {
        return listaUsuarios.stream().anyMatch(u -> u.getUsuario().equals(usuario));
    }

    @Override
    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, Subasta subasta, String usuario, String contrasenia, String email, Boolean isAutenticado) throws AnuncianteException, UsuarioEnUsoException {
        if (obtenerAnunciante(identificacion) != null)
            throw new AnuncianteException("El Usuario de Anunciante ya se encuentra creado");
        if (verificarUsuario(usuario)) throw new UsuarioEnUsoException();
        if (edad < 18) throw new AnuncianteException("Debe ser mayor de edad para registrarse");
        Anunciante nuevoAnunciante = new Anunciante(nombres, apellidos, identificacion, edad, this, usuario, contrasenia, email, isAutenticado, TipoUsuario.ANUNCIANTE);
        listaUsuarios.add(nuevoAnunciante);
        return true;
    }

    // Método para imprimir los detalles de un anunciante en particular
    @Override
    public void imprimirAnunciante(String identificacion) throws AnuncianteException {
        Anunciante a = obtenerAnunciante(identificacion);
        if (a == null) throw new AnuncianteException("El Usuario de Anunciante No se encuentra creado");
        System.out.println("Detalles del Anunciante:");
        System.out.println("Nombres: " + a.getNombres());
        System.out.println("Apellidos: " + a.getApellidos());
        System.out.println("Identificación: " + a.getIdentificacion());
    }

    // Método para eliminar un anunciante de la lista de usuarios
    @Override
    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException {
        Anunciante a = obtenerAnunciante(identificacion);
        if (a == null) throw new AnuncianteException("El Usuario de Anunciante No se encuentra creado");
        listaUsuarios.remove(a);
        return true;
    }

    @Override
    public boolean actualizarAnunciante(String nombres, String apellidos, String identificacion, int edad, String email) throws AnuncianteException {
        Anunciante a = obtenerAnunciante(identificacion);
        if (a == null) throw new AnuncianteException("El Usuario de Anunciante No se encuentra creado");
        if (edad < 18) throw new AnuncianteException("Debe ser mayor de edad para registrarse");
        a.setNombres(nombres);
        a.setApellidos(apellidos);
        a.setEdad(edad);
        a.setEmail(email);
        return true;
    }

    @Override
    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, Subasta subasta, String usuario, String contrasenia, String email, Boolean isAutenticado) throws CompradorException, UsuarioEnUsoException {
        if (obtenerComprador(identificacion) != null)
            throw new CompradorException("El Usuario de Comprador ya se encuentra creado");
        if (verificarUsuario(usuario)) throw new UsuarioEnUsoException();
        if (edad < 18) throw new CompradorException("Debe ser mayor de edad para registrarse");
        Comprador nuevoComprador = new Comprador(nombres, apellidos, identificacion, edad, this, usuario, contrasenia, email, isAutenticado, TipoUsuario.COMPRADOR);
        listaUsuarios.add(nuevoComprador);
        return true;
    }

    @Override
    public void imprimirComprador(String identificacion) throws CompradorException {
        Comprador c = obtenerComprador(identificacion);
        if (c == null) throw new CompradorException("El Usuario de Comprador No se encuentra creado");
        System.out.println("Detalles del Anunciante:");
        System.out.println("Nombres: " + c.getNombres());
        System.out.println("Apellidos: " + c.getApellidos());
        System.out.println("Identificación: " + c.getIdentificacion());

    }

    @Override
    public boolean eliminarComprador(String identificacion) throws CompradorException {
        Comprador c = obtenerComprador(identificacion);
        if (c == null) throw new CompradorException("El Usuario de Comprador No se encuentra creado");
        listaUsuarios.remove(c);
        return true;
    }

    @Override
    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad, String email) throws CompradorException {
        Comprador c = obtenerComprador(identificacion);
        if (c == null) throw new CompradorException("El Usuario de Comprador No se encuentra creado");
        if (edad < 18) throw new CompradorException("Debe ser mayor de edad para registrarse");
        c.setNombres(nombres);
        c.setApellidos(apellidos);
        c.setEdad(edad);
        c.setEmail(email);
        return true;
    }

    public String cambiarUsuario(String identificacion, TipoUsuario tipoUsuario, String nuevoUsuario) throws AnuncianteException, UsuarioEnUsoException, CompradorException {
        if (tipoUsuario.equals(TipoUsuario.ANUNCIANTE)) {
            Anunciante a = obtenerAnunciante(identificacion);
            if (a == null) throw new AnuncianteException("El Usuario de Anunciante No se encenutra creado");
            if (verificarUsuario(nuevoUsuario)) throw new UsuarioEnUsoException();
            a.setUsuario(nuevoUsuario);
        }
        if (tipoUsuario.equals(TipoUsuario.COMPRADOR)) {
            Comprador c = obtenerComprador(identificacion);
            if (c == null) throw new CompradorException("El Usuario de Comprador No se encenutra creado");
            if (verificarUsuario(nuevoUsuario)) throw new UsuarioEnUsoException();
            c.setUsuario(nuevoUsuario);
        }
        return "Usuario actualizado correctamente";
    }

    public String cambiarContrasenia(String identificacion, TipoUsuario tipoUsuario, String nuevaContrasenia) throws AnuncianteException, CompradorException {
        if (tipoUsuario.equals(TipoUsuario.ANUNCIANTE)) {
            Anunciante a = obtenerAnunciante(identificacion);
            if (a == null) throw new AnuncianteException("El Usuario de Anunciante No se encenutra creado");
            a.setContrasenia(nuevaContrasenia);
        }
        if (tipoUsuario.equals(TipoUsuario.COMPRADOR)) {
            Comprador c = obtenerComprador(identificacion);
            if (c == null) throw new CompradorException("El Usuario de Comprador No se encenutra creado");
            c.setContrasenia(nuevaContrasenia);
        }
        return "Contraseña actualizada correctamente";
    }

    //Metodos para autenticar y desautenticar un usuario una vez inicia sesión
    public void autenticarUsuario(String usuario) {
        Usuario u = listaUsuarios.get(encontrarPosUsuario(usuario));
        u.setAutenticado(true);
    }

    public void desAutenticarUsuario(String usuario) {
        Usuario u = listaUsuarios.get(encontrarPosUsuario(usuario));
        u.setAutenticado(false);
    }

    //Metodos adicionales para manejo de fechas ---------------------------------------------------------------------------------------------------------------------------------

    /**
     * Metodo para devolver el dia de una fecha en formato String dd.mm.aa como Integer
     * @param fecha
     * @return
     */
    public static int devolverDiaFecha(String fecha)
    {
        String dia=fecha.substring(0,fecha.indexOf("."));
        return Integer.parseInt(dia);
    }
    /**
     * Metodo para devolver el mes de una fecha en formato String dd.mm.aa como Integer
     * @param fecha
     * @return
     */
    public static int devolverMesFecha(String fecha)
    {
        String mes=fecha.substring(fecha.indexOf(".")+1,fecha.lastIndexOf("."));
        return Integer.parseInt(mes);
    }
    /**
     * Metodo para devolver el año de una fecha en formato String dd.mm.aa como Integer
     * @param fecha
     * @return
     */
    public static int devolverAnioFecha(String fecha)
    {
        String anio=fecha.substring(fecha.lastIndexOf(".")+1,fecha.length());
        return Integer.parseInt(anio);
    }
    /**
     * Metodo para verificar si una fecha esta entre la fechaIncio y la fechaFinal del parametro
     * @param fecha
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public static boolean isEntreFechas(String fecha, String fechaInicio, String fechaFinal)
    {
        boolean centinela=false;
        int anioFecha=devolverAnioFecha(fecha); int mesFecha=devolverMesFecha(fecha); int diaFecha=devolverDiaFecha(fecha);
        int anioFechaInicio=devolverAnioFecha(fechaInicio); int mesFechaInicio=devolverMesFecha(fechaInicio); int diaFechaInicio=devolverDiaFecha(fechaInicio);
        int anioFechaFinal=devolverAnioFecha(fechaFinal); int mesFechaFinal=devolverMesFecha(fechaFinal); int diaFechaFinal=devolverDiaFecha(fechaFinal);
        if(mesFechaInicio==mesFechaFinal && anioFechaInicio==anioFechaFinal && mesFecha==mesFechaInicio && anioFecha==anioFechaInicio && diaFecha>=diaFechaInicio && diaFecha<=diaFechaFinal)
        {
            centinela=true;
        }
        else
        if(mesFechaInicio!=mesFechaFinal && anioFechaInicio==anioFechaFinal &&((diaFecha>=diaFechaInicio && mesFecha==mesFechaInicio && anioFecha==anioFechaInicio)||(mesFecha>mesFechaInicio && mesFecha<mesFechaFinal &&
                anioFecha==anioFechaInicio)||(diaFecha<=diaFechaFinal && mesFecha==mesFechaFinal && anioFecha==anioFechaFinal)))
        {
            centinela=true;
        }
        else
        if(anioFechaInicio!=anioFechaFinal&&((diaFecha>=diaFechaInicio && mesFecha==mesFechaInicio && anioFecha==anioFechaInicio)||(diaFecha<=diaFechaFinal && mesFecha==mesFechaFinal && anioFecha==anioFechaFinal)
                ||(anioFecha>anioFechaInicio && anioFecha<anioFechaFinal)||(mesFecha>mesFechaInicio && anioFecha==anioFechaInicio)||(mesFecha<mesFechaFinal && anioFecha==anioFechaFinal)))
        {
            centinela=true;
        }
        return centinela;
    }
}
