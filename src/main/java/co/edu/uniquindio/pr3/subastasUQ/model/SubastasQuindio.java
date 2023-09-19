package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.ISubastasQuindio;

import java.util.ArrayList;
import java.util.List;

public class SubastasQuindio implements ISubastasQuindio {

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
        this.listaProductos = new ArrayList<Producto>();
        this.listaAnuncios = new ArrayList<Anuncio>();
        this.listaUsuarios = new ArrayList<Usuario>();
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

    //---------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos de la Clase SubastasQuindio

    //Metodo para obtener un Anunciante en la lista de Usuarios de SubastasQuindio
    public Anunciante obtenerAnunciante(String identificacion) {
        Anunciante a = null;
        try {
            a = (Anunciante) listaUsuarios.stream().filter(u -> u.getIdentificacion().equals(identificacion) && u.getTipoUsuario().equals(TipoUsuario.ANUNCIANTE)).findFirst().get();
        }
        catch(Exception ignored) {

        }
        return a;
    }

    //Metodo para obtener un Comprador en la lista de Usuarios de SubastasQuindio
    public Comprador obtenerComprador(String identificacion) {
        Comprador c = null;
        try {
            c = (Comprador) listaUsuarios.stream().filter(u -> u.getIdentificacion().equals(identificacion) && u.getTipoUsuario().equals(TipoUsuario.COMPRADOR)).findFirst().get();
        }
        catch(Exception ignored) {

        }
        return c;
    }

    //Metodo para verificar si un usuario esta en uso
    public boolean verificarUsuario(String usuario) {
        return listaUsuarios.stream().anyMatch(u -> u.getUsuario().equals(usuario));
    }

    @Override
    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, Boolean isAutenticado) throws AnuncianteException, UsuarioEnUsoException {
        if(obtenerAnunciante(identificacion)!=null) throw new AnuncianteException("El Usuario de Anunciante ya se encuentra creado");
        if(verificarUsuario(usuario)) throw new UsuarioEnUsoException();
        Anunciante nuevoAnunciante = new Anunciante(nombres, apellidos, identificacion, edad, usuario, contrasenia, isAutenticado, TipoUsuario.ANUNCIANTE);
        listaUsuarios.add(nuevoAnunciante);
        return true;
    }

    // Método para imprimir los detalles de un anunciante en particular
    @Override
    public void imprimirAnunciante(String identificacion) throws AnuncianteException {
        Anunciante a = obtenerAnunciante(identificacion);
        if(a==null) throw new AnuncianteException("El Usuario de Anunciante No se encuentra creado");
        System.out.println("Detalles del Anunciante:");
        System.out.println("Nombres: " + a.getNombres());
        System.out.println("Apellidos: " + a.getApellidos());
        System.out.println("Identificación: " + a.getIdentificacion());
    }

    // Método para eliminar un anunciante de la lista de usuarios
    @Override
    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException {
        Anunciante a = obtenerAnunciante(identificacion);
        if(a==null) throw new AnuncianteException("El Usuario de Anunciante No se encuentra creado");
        listaUsuarios.remove(a);
        return true;
    }

    @Override
    public boolean actualizarAnunciante(String nombres, String apellidos, String identificacion, int edad) throws AnuncianteException {
        Anunciante a = obtenerAnunciante(identificacion);
        if(a==null) throw new AnuncianteException("El Usuario de Anunciante No se encuentra creado");
        a.setNombres(nombres);
        a.setApellidos(apellidos);
        a.setEdad(edad);
        return true;
    }

    @Override
    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, Boolean isAutenticado) throws CompradorException, UsuarioEnUsoException {
        if(obtenerComprador(identificacion)!=null) throw new CompradorException("El Usuario de Comprador ya se encuentra creado");
        if(verificarUsuario(usuario)) throw new UsuarioEnUsoException();
        Comprador nuevoComprador = new Comprador(nombres, apellidos, identificacion, edad, usuario, contrasenia, isAutenticado, TipoUsuario.COMPRADOR);
        listaUsuarios.add(nuevoComprador);
        return true;
    }

    @Override
    public void imprimirComprador(String identificacion) throws CompradorException {
        Comprador c = obtenerComprador(identificacion);
        if(c==null) throw new CompradorException("El Usuario de Comprador No se encuentra creado");
        System.out.println("Detalles del Anunciante:");
        System.out.println("Nombres: " + c.getNombres());
        System.out.println("Apellidos: " + c.getApellidos());
        System.out.println("Identificación: " + c.getIdentificacion());

    }

    @Override
    public boolean eliminarComprador(String identificacion) throws CompradorException {
        Comprador c = obtenerComprador(identificacion);
        if(c==null) throw new CompradorException("El Usuario de Comprador No se encuentra creado");
        listaUsuarios.remove(c);
        return true;
    }

    @Override
    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad) throws CompradorException {
        Comprador c = obtenerComprador(identificacion);
        if(c==null) throw new CompradorException("El Usuario de Comprador No se encuentra creado");
        c.setNombres(nombres);
        c.setApellidos(apellidos);
        c.setEdad(edad);
        return true;
    }

    public String cambiarUsuario(String identificacion, TipoUsuario tipoUsuario, String nuevoUsuario) throws AnuncianteException, UsuarioEnUsoException, CompradorException {
        if(tipoUsuario.equals(TipoUsuario.ANUNCIANTE)){
            Anunciante a = obtenerAnunciante(identificacion);
            if(a==null) throw new AnuncianteException("El Usuario de Anunciante No se encenutra creado");
            if(verificarUsuario(nuevoUsuario)) throw new UsuarioEnUsoException();
            a.setUsuario(nuevoUsuario);
        }
        if(tipoUsuario.equals(TipoUsuario.COMPRADOR)){
            Comprador c = obtenerComprador(identificacion);
            if(c==null) throw new CompradorException("El Usuario de Comprador No se encenutra creado");
            if(verificarUsuario(nuevoUsuario)) throw new UsuarioEnUsoException();
            c.setUsuario(nuevoUsuario);
        }
        return "Usuario cambiado correctamente";
    }

    public String cambiarContrasenia(String identificacion, TipoUsuario tipoUsuario, String nuevaContrasenia) throws AnuncianteException, CompradorException {
        if(tipoUsuario.equals(TipoUsuario.ANUNCIANTE)){
            Anunciante a = obtenerAnunciante(identificacion);
            if(a==null) throw new AnuncianteException("El Usuario de Anunciante No se encenutra creado");
            a.setContrasenia(nuevaContrasenia);
        }
        if(tipoUsuario.equals(TipoUsuario.COMPRADOR)){
            Comprador c = obtenerComprador(identificacion);
            if(c==null) throw new CompradorException("El Usuario de Comprador No se encenutra creado");
            c.setContrasenia(nuevaContrasenia);
        }
        return "Usuario cambiado correctamente";
    }
}
