package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.services.IModelFactoryControllerService;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements IModelFactoryControllerService {

    //Datos para el manejo de controladores segun la ventana
    private SubastasQuindio miSubastasQuindio;
    private SubastasQuindioViewController subastasQuindioViewController;
    private RegistroViewController registroViewController;
    private LoginViewController loginViewController;
    private MiCuentaViewController miCuentaViewController;

    private ProductosViewController productosViewController;

    //Datos para el manejo de usuarios segun la verificacion de usuario
    private Anunciante miAnunciante;
    private Comprador miComprador;

    //Datos para la creación de productos en la aplicación

    private Producto miProducto;

    //Singleton (Garantiza instancia unica)
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aqu� al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Metodo para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
        inicializarDatos();
    }

    private void inicializarDatos() {
        miSubastasQuindio = new SubastasQuindio("Subastas UQ", "Carrera 15 #12N, Armenia, Quindío");
    }

    //setters() & getters del Singleton del Concesionario

    public SubastasQuindio getMiSubastasQuindio() {
        return miSubastasQuindio;
    }

    public void setMiSubastasQuindio(SubastasQuindio miSubastasQuindio) {
        this.miSubastasQuindio = miSubastasQuindio;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    //Funciones de subastasQuindio para el singleton
    //Concesionario jeje

    public void initSubastasQuindioViewController(SubastasQuindioViewController subastasQuindioViewController) {
        this.subastasQuindioViewController = subastasQuindioViewController;
    }

    public void initRegistroViewController(RegistroViewController registroViewController) {
        this.registroViewController = registroViewController;
    }

    public void initLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public void initMiCuentaViewController(MiCuentaViewController miCuentaViewController) {
        this.miCuentaViewController = miCuentaViewController;
    }

    public void initProductosViewController(ProductosViewController productosViewController) {
        this.productosViewController = productosViewController;
    }

    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, AnuncianteException {
        return this.miSubastasQuindio.crearAnunciante(nombres, apellidos, identificacion, edad, this.miSubastasQuindio, usuario, contrasenia, email, false);
    }

    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, CompradorException {
        return this.miSubastasQuindio.crearComprador(nombres, apellidos, identificacion, edad, this.miSubastasQuindio, usuario, contrasenia, email, false);
    }


    public int encontrarPosUsuario(String usuario) {
        return this.miSubastasQuindio.encontrarPosUsuario(usuario);
    }

    public Usuario obtenerUsuario(int pos) {
        return this.miSubastasQuindio.getListaUsuarios().get(pos);
    }

    public Anunciante obtenerAnunciante(String identificacion) {
        return this.miSubastasQuindio.obtenerAnunciante(identificacion);
    }

    public Comprador obtenerComprador(String identificacion) {
        return this.miSubastasQuindio.obtenerComprador(identificacion);
    }

    public void setMiAnunciante(Anunciante miAnunciante) {
        this.miAnunciante = miAnunciante;
        this.miComprador = null;
        this.miCuentaViewController.setAnunciante(miAnunciante);
        this.miCuentaViewController.setMiAnucianteInformation();
        this.miSubastasQuindio.autenticarUsuario(miAnunciante.getUsuario());
    }

    public void setMiComprador(Comprador miComprador) {
        this.miComprador = miComprador;
        this.miAnunciante = null;
        this.miCuentaViewController.setComprador(miComprador);
        this.miCuentaViewController.setMiCompradorInformation();
        this.miSubastasQuindio.autenticarUsuario(miComprador.getUsuario());
    }

    public void resetCuenta(String usuario) {
        this.miAnunciante = null;
        this.miComprador = null;
        this.miSubastasQuindio.desAutenticarUsuario(usuario);
        this.subastasQuindioViewController.dehabilitarPestanias();
    }

    public void resetCuenta() {
        this.miAnunciante = null;
        this.miComprador = null;
        this.subastasQuindioViewController.dehabilitarPestanias();
    }

    public void habilitarPestaniasAnunciante() {
        subastasQuindioViewController.habilitarPestaniasAnunciante();
    }

    public void habilitarPestaniasComprador() {
        subastasQuindioViewController.habilitarPestaniasComprador();
    }

    public boolean actualizarAnuciante(String nombres, String apellidos, String identificacion, int edad, String email) throws AnuncianteException {
        return miSubastasQuindio.actualizarAnunciante(nombres, apellidos, identificacion, edad, email);
    }

    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad, String email) throws CompradorException {
        return miSubastasQuindio.actualizarComprador(nombres, apellidos, identificacion, edad, email);
    }

    public String cambiarContrasenia(String identifiacion, TipoUsuario tipoUsuario, String nuevaContrasenia) throws CompradorException, AnuncianteException {
        return miSubastasQuindio.cambiarContrasenia(identifiacion, tipoUsuario, nuevaContrasenia);
    }

    public String cambiarUsuario(String identifiacion, TipoUsuario tipoUsuario, String nuevoUsuario) throws UsuarioEnUsoException, CompradorException, AnuncianteException {
        return miSubastasQuindio.cambiarUsuario(identifiacion, tipoUsuario, nuevoUsuario);
    }

    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException {
        return miSubastasQuindio.eliminarAnunciante(identificacion);
    }

    public boolean eliminarComprador(String identificacion) throws CompradorException {
        return miSubastasQuindio.eliminarComprador(identificacion);
    }

    //Métodos para crear un producto

    public Producto crearProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        return this.miProducto.crearProducto(codigo, nombre, descripcion, image, valorInicial, tipoProducto);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------

}
