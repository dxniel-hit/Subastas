package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.mappers.*;
import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.IModelFactoryControllerService;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.utils.*;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.*;
import javafx.scene.image.Image;

import java.util.*;

public class ModelFactoryController implements IModelFactoryControllerService {

    //Datos para el manejo de controladores segun la ventana
    private VentanaPrincipalViewController ventanaPrincipalViewController;
    private RegistroViewController registroViewController;
    private LoginViewController loginViewController;
    private MiCuentaViewController miCuentaViewController;


    //Datos para el manejo de usuarios segun la verificacion de usuario
    private Anunciante miAnunciante;
    private Comprador miComprador;

    //Datos para la creación de productos en la aplicación
    //DTO
    Subasta miSubasta;
    ArrayList<Producto> listaProductos = new ArrayList<Producto>();
    SubastaMappers mapper = SubastaMappers.INSTANCE;
    private Producto miProducto;

    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
        cargarDatosBase();
    }
    //Singleton (Garantiza instancia unica)
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }

    private void cargarDatosBase() {
        miSubasta = new Subasta();
        miSubasta.setListaProductos(listaProductos);
        miSubasta = SubastaUtils.inicializarDatos();
   }

    @Override
    public List<ProductoDTO> obtenerProductos() {
        return mapper.getProductosDTO(miSubasta.getListaProductos());
    }

    // Método para obtener la instancia de nuestra clase

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private void inicializarDatos() {
        miSubasta = new Subasta("Subastas UQ", "Carrera 15 #12N, Armenia, Quindío");
    }

    //Getterws y setters del subastero

    public Subasta getMiSubasta() {

        return miSubasta;
    }

    public void setMiSubasta(Subasta miSubasta) {

        this.miSubasta = miSubasta;
    }

    public Subasta getMiSubastasQuindio() {
        return miSubasta;
    }

    public void setMiSubastasQuindio(Subasta miSubasta) {
        this.miSubasta = miSubasta;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    //Funciones de subastasQuindio para el singleton
    public void initVentanaPrincipalViewController(VentanaPrincipalViewController ventanaPrincipalViewController) {
        this.ventanaPrincipalViewController = ventanaPrincipalViewController;
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


    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, AnuncianteException {
        return this.miSubasta.crearAnunciante(nombres, apellidos, identificacion, edad, this.miSubasta, usuario, contrasenia, email, false);
    }

    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, CompradorException {
        return this.miSubasta.crearComprador(nombres, apellidos, identificacion, edad, this.miSubasta, usuario, contrasenia, email, false);
    }
    public int encontrarPosUsuario(String usuario) {
        return this.miSubasta.encontrarPosUsuario(usuario);
    }

    public Usuario obtenerUsuario(int pos) {
        return this.miSubasta.getListaUsuarios().get(pos);
    }

    public Anunciante obtenerAnunciante(String identificacion) {
        return this.miSubasta.obtenerAnunciante(identificacion);
    }

    public Comprador obtenerComprador(String identificacion) {
        return this.miSubasta.obtenerComprador(identificacion);
    }

    public void setMiAnunciante(Anunciante miAnunciante) {
        this.miAnunciante = miAnunciante;
        this.miComprador = null;
        this.miCuentaViewController.setAnunciante(miAnunciante);
        this.miCuentaViewController.setMiAnucianteInformation();
        this.miSubasta.autenticarUsuario(miAnunciante.getUsuario());
    }

    public void setMiComprador(Comprador miComprador) {
        this.miComprador = miComprador;
        this.miAnunciante = null;
        this.miCuentaViewController.setComprador(miComprador);
        this.miCuentaViewController.setMiCompradorInformation();
        this.miSubasta.autenticarUsuario(miComprador.getUsuario());
    }

    public void resetCuenta(String usuario) {
        this.miAnunciante = null;
        this.miComprador = null;
        this.miSubasta.desAutenticarUsuario(usuario);
        this.ventanaPrincipalViewController.dehabilitarPestanias();
    }

    public void resetCuenta() {
        this.miAnunciante = null;
        this.miComprador = null;
        this.ventanaPrincipalViewController.dehabilitarPestanias();
    }

    public void habilitarPestaniasAnunciante() {
        ventanaPrincipalViewController.habilitarPestaniasAnunciante();
    }

    public void habilitarPestaniasComprador() {
        ventanaPrincipalViewController.habilitarPestaniasComprador();
    }

    public boolean actualizarAnuciante(String nombres, String apellidos, String identificacion, int edad, String email) throws AnuncianteException {
        return miSubasta.actualizarAnunciante(nombres, apellidos, identificacion, edad, email);
    }

    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad, String email) throws CompradorException {
        return miSubasta.actualizarComprador(nombres, apellidos, identificacion, edad, email);
    }

    public String cambiarContrasenia(String identifiacion, TipoUsuario tipoUsuario, String nuevaContrasenia) throws CompradorException, AnuncianteException {
        return miSubasta.cambiarContrasenia(identifiacion, tipoUsuario, nuevaContrasenia);
    }

    public String cambiarUsuario(String identifiacion, TipoUsuario tipoUsuario, String nuevoUsuario) throws UsuarioEnUsoException, CompradorException, AnuncianteException {
        return miSubasta.cambiarUsuario(identifiacion, tipoUsuario, nuevoUsuario);
    }

    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException {
        return miSubasta.eliminarAnunciante(identificacion);
    }

    public boolean eliminarComprador(String identificacion) throws CompradorException {
        return miSubasta.eliminarComprador(identificacion);
    }

    //Métodos para crear un producto

    public Producto crearProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        return this.miProducto.crearProducto(codigo, nombre, descripcion, image, valorInicial, tipoProducto);
    }

}
