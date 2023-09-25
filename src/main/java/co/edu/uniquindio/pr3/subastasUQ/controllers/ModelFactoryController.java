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
    private ProductosViewController productosViewController;


    //Datos para el manejo de usuarios segun la verificacion de usuario
    private Anunciante miAnunciante;
    private Comprador miComprador;

    //Datos para la creación de productos en la aplicación
    //DTO
    Subasta miSubasta;
    SubastaMappers mapper = SubastaMappers.INSTANCE;

    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
        inicializarDatos();
    }

    //Singleton (Garantiza instancia unica)
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

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

    public Anunciante getMiAnunciante() {
        return miAnunciante;
    }

    public Comprador getMiComprador() {
        return miComprador;
    }

    public Subasta getMiSubastasQuindio() {
        return miSubasta;
    }

    public void setMiSubastasQuindio(Subasta miSubasta) {
        this.miSubasta = miSubasta;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    //Funciones de subastasQuindio para el singleton

    @Override
    public void initVentanaPrincipalViewController(VentanaPrincipalViewController ventanaPrincipalViewController) {
        this.ventanaPrincipalViewController = ventanaPrincipalViewController;
    }

    @Override
    public void initRegistroViewController(RegistroViewController registroViewController) {
        this.registroViewController = registroViewController;
    }

    @Override
    public void initLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    @Override
    public void initMiCuentaViewController(MiCuentaViewController miCuentaViewController) {
        this.miCuentaViewController = miCuentaViewController;
    }

    @Override
    public void initProductosViewControlles(ProductosViewController productosViewController) {
        this.productosViewController = productosViewController;
    }

    @Override
    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, AnuncianteException {
        return this.miSubasta.crearAnunciante(nombres, apellidos, identificacion, edad, this.miSubasta, usuario, contrasenia, email, false);
    }

    @Override
    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, CompradorException {
        return this.miSubasta.crearComprador(nombres, apellidos, identificacion, edad, this.miSubasta, usuario, contrasenia, email, false);
    }

    @Override
    public int encontrarPosUsuario(String usuario) {
        return this.miSubasta.encontrarPosUsuario(usuario);
    }

    @Override
    public Usuario obtenerUsuario(int pos) {
        return this.miSubasta.getListaUsuarios().get(pos);
    }

    @Override
    public Anunciante obtenerAnunciante(String identificacion) {
        return this.miSubasta.obtenerAnunciante(identificacion);
    }

    @Override
    public Comprador obtenerComprador(String identificacion) {
        return this.miSubasta.obtenerComprador(identificacion);
    }

    @Override
    public void setMiAnunciante(Anunciante miAnunciante) {
        this.miAnunciante = miAnunciante;
        this.miComprador = null;
        this.miCuentaViewController.setAnunciante(miAnunciante);
        this.miCuentaViewController.setMiAnucianteInformation();
        this.miSubasta.autenticarUsuario(miAnunciante.getUsuario());
        this.productosViewController.setAnunciante(miAnunciante);
        this.productosViewController.initView();
    }

    @Override
    public void setMiComprador(Comprador miComprador) {
        this.miComprador = miComprador;
        this.miAnunciante = null;
        this.miCuentaViewController.setComprador(miComprador);
        this.miCuentaViewController.setMiCompradorInformation();
        this.miSubasta.autenticarUsuario(miComprador.getUsuario());
    }

    @Override
    public void resetCuenta(String usuario) {
        this.miAnunciante = null;
        this.miComprador = null;
        this.miCuentaViewController.setAnunciante(null);
        this.miCuentaViewController.setComprador(null);
        this.productosViewController.setAnunciante(null);
        this.miSubasta.desAutenticarUsuario(usuario);
        this.ventanaPrincipalViewController.dehabilitarPestanias();
    }

    @Override
    public void resetCuenta() {
        this.miAnunciante = null;
        this.miComprador = null;
        this.ventanaPrincipalViewController.dehabilitarPestanias();
    }

    @Override
    public void habilitarPestaniasAnunciante() {
        ventanaPrincipalViewController.habilitarPestaniasAnunciante();
    }

    @Override
    public void habilitarPestaniasComprador() {
        ventanaPrincipalViewController.habilitarPestaniasComprador();
    }

    @Override
    public boolean actualizarAnuciante(String nombres, String apellidos, String identificacion, int edad, String email) throws AnuncianteException {
        return miSubasta.actualizarAnunciante(nombres, apellidos, identificacion, edad, email);
    }

    @Override
    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad, String email) throws CompradorException {
        return miSubasta.actualizarComprador(nombres, apellidos, identificacion, edad, email);
    }

    @Override
    public String cambiarContrasenia(String identifiacion, TipoUsuario tipoUsuario, String nuevaContrasenia) throws CompradorException, AnuncianteException {
        return miSubasta.cambiarContrasenia(identifiacion, tipoUsuario, nuevaContrasenia);
    }

    @Override
    public String cambiarUsuario(String identifiacion, TipoUsuario tipoUsuario, String nuevoUsuario) throws UsuarioEnUsoException, CompradorException, AnuncianteException {
        return miSubasta.cambiarUsuario(identifiacion, tipoUsuario, nuevoUsuario);
    }

    @Override
    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException {
        return miSubasta.eliminarAnunciante(identificacion);
    }

    @Override
    public boolean eliminarComprador(String identificacion) throws CompradorException {
        return miSubasta.eliminarComprador(identificacion);
    }

    //Métodos para crear un producto

    public List<ProductoDTO> obtenerProductosAnunciante() {
        if(miAnunciante!=null) return mapper.getProductosDTO(miAnunciante.getListaProductos());
        return null;
    }

    public boolean agregarProducto(ProductoDTO productoDTO) {
        Producto p = mapper.productoDTOtoProducto(productoDTO);
        try {
            return miAnunciante.crearProducto(p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getImage(), p.getValorInicial(), p.getTipoProducto());
        } catch (ProductoException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
