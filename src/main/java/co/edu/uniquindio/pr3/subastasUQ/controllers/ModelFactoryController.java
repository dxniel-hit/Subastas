package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.hilos.*;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.mappers.*;
import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.IModelFactoryControllerService;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.persistencia.*;
import co.edu.uniquindio.pr3.subastasUQ.rabbitmq.config.RabbitFactory;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.nio.charset.StandardCharsets;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static co.edu.uniquindio.pr3.subastasUQ.rabbitmq.utils.Constantes.QUEUE_CONSUMIDOR;
import static co.edu.uniquindio.pr3.subastasUQ.rabbitmq.utils.Constantes.QUEUE_PRODUCTOR;

public class ModelFactoryController implements IModelFactoryControllerService, Runnable {

    //Datos para el manejo de controladores segun la ventana
    private VentanaPrincipalViewController ventanaPrincipalViewController;
    private RegistroViewController registroViewController;
    private LoginViewController loginViewController;
    private MiCuentaViewController miCuentaViewController;
    private ProductosViewController productosViewController;
    private MisAnunciosViewController misAnunciosViewController;
    private SubastasViewController subastasViewController;
    private MisPujasViewController misPujasViewController;

    //Clase Subasta global
    static Subasta miSubasta;

    //Datos para el manejo de usuarios segun la verificacion de usuario
    private Anunciante miAnunciante;
    private Comprador miComprador;
    private Anuncio anuncioSeleccionado;

    //Datos para la creación de cualquier transaccion (CRUD) en la aplicación
    //DTO
    ProductoMapper mapper = ProductoMapper.INSTANCE;
    PujaMapper mapperPuja = PujaMapper.INSTANCE;
    AnuncioMapper mapperAnuncio = AnuncioMapper.INSTANCE;

    //Datos para flujo de datos
    static Logger LOGGER = Log.LOGGER;

    //Direcciones de archivos para serializar y deserializar
    public static final String RUTA_ARCHIVO_SUBASTAUQXML = "src/main/resources/persistencia/SubastasUQ.xml";
    public static final String RUTA_ARCHIVO_SUBASTAUQDAT = "src/main/resources/persistencia/SubastasUQ.dat";

    //Manejo de rabbit mq
    RabbitFactory rabbitFactory;
    ConnectionFactory connectionFactory;
    //Hilos para consumidor
    Thread hiloServicioConsumer;

    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
        //Se inicializan los datos
        try {
            inicializarDatos();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Se inicializa la conexion con rabbitmq
        initRabbitConnection();
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

    private void initRabbitConnection() {
        rabbitFactory = new RabbitFactory();
        connectionFactory = rabbitFactory.getConnectionFactory();
        System.out.println("conexion establecidad");
    }

    private void inicializarDatos() throws InterruptedException {
        miSubasta = new Subasta("Subastas UQ", "Carrera 15 #12N, Armenia, Quindío");

        //iniciarYSalvarDatosPrueba()
        IniciarYSalvarDatosPruebaThread initThread = new IniciarYSalvarDatosPruebaThread();
        initThread.start();
        initThread.join();
        //cargarDatosDesdeArchivos()
        CargarDatosArchivosThread cargarArchivosThread = new CargarDatosArchivosThread();
        cargarArchivosThread.start();
        cargarArchivosThread.join();

        //Se obtiene la informacion de los archivos serializados
        //cargarResourceXML()
        CargarXMLThread cargarXMLThread = new CargarXMLThread();
        cargarXMLThread.start();
        cargarXMLThread.join();

        //cargarResourceBinario()
        /*CargarBinarioThread cargarBinarioThread = new CargarBinarioThread();
        cargarBinarioThread.start();
        cargarBinarioThread.join();*/
    }

    //Getters y setters del subastero
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

    public void initMisAnunciosViewController(MisAnunciosViewController misAnunciosViewController) {
        this.misAnunciosViewController = misAnunciosViewController;
    }

    public void initSubastasViewController(SubastasViewController subastasViewController) {
        this.subastasViewController = subastasViewController;
    }

    public void initMisPujasViewController(MisPujasViewController misPujasViewController) {
        this.misPujasViewController = misPujasViewController;
    }

    @Override
    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, AnuncianteException {
        return miSubasta.crearAnunciante(nombres, apellidos, identificacion, edad, miSubasta, usuario, contrasenia, email, false);
    }

    @Override
    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, CompradorException {
        return miSubasta.crearComprador(nombres, apellidos, identificacion, edad, miSubasta, usuario, contrasenia, email, false);
    }

    @Override
    public int encontrarPosUsuario(String usuario) {
        return miSubasta.encontrarPosUsuario(usuario);
    }

    @Override
    public Usuario obtenerUsuario(int pos) {
        return miSubasta.getListaUsuarios().get(pos);
    }

    @Override
    public Anunciante obtenerAnunciante(String identificacion) {
        return miSubasta.obtenerAnunciante(identificacion);
    }

    @Override
    public Comprador obtenerComprador(String identificacion) {
        return miSubasta.obtenerComprador(identificacion);
    }

    @Override
    public void setMiAnunciante(Anunciante miAnunciante) {
        this.miAnunciante = miAnunciante;
        this.miComprador = null;

        //se setea la informacion para CuentaViewController
        this.miCuentaViewController.setAnunciante(miAnunciante);
        this.miCuentaViewController.setMiAnucianteInformation();
        miSubasta.autenticarUsuario(miAnunciante.getUsuario());

        //se setea el anunciante en ProductosViewController
        this.productosViewController.setAnunciante(miAnunciante);
        //se añaden los productos segun el anunciante
        ObservableList<ProductoDTO> listaProductosDTO = FXCollections.observableArrayList();
        listaProductosDTO.addAll(obtenerProductosAnunciante());
        this.productosViewController.setListaProductosDTO(listaProductosDTO);

        //se setea el anunciante en MisAnunciosViewController
        this.misAnunciosViewController.setAnunciante(miAnunciante);
        //se añaden los anuncios segun el anunciante
        ObservableList<AnuncioDTO> listaAnunciosDTO = FXCollections.observableArrayList();
        listaAnunciosDTO.addAll(obtenerAnunciosAnunciante());
        this.misAnunciosViewController.setListaAnunciosDTO(listaAnunciosDTO);
        //se añaden los productos segun el anunciante
        this.misAnunciosViewController.setListaProductosDTO(listaProductosDTO);
        //se añaden las pujas segun el anunciante
        this.misAnunciosViewController.setListaPujasDTO(FXCollections.observableArrayList());

        //se setean los anuncios en SubastasViewController
        ObservableList<AnuncioDTO> listaSubastasDTO = FXCollections.observableArrayList();
        listaSubastasDTO.addAll(mapperAnuncio.getAnunciosDTO(miSubasta.getListaAnuncios()));
        this.subastasViewController.setListaAnunciosDTO(listaSubastasDTO);
    }

    @Override
    public void setMiComprador(Comprador miComprador) {
        this.miComprador = miComprador;
        this.miAnunciante = null;

        //se setea la informacion para CuentaViewController
        this.miCuentaViewController.setComprador(miComprador);
        this.miCuentaViewController.setMiCompradorInformation();
        miSubasta.autenticarUsuario(miComprador.getUsuario());

        //se setean los anuncios en SubastasViewController
        ObservableList<AnuncioDTO> listaSubastasDTO = FXCollections.observableArrayList();
        listaSubastasDTO.addAll(mapperAnuncio.getAnunciosDTO(miSubasta.getListaAnuncios()));
        this.subastasViewController.setListaAnunciosDTO(listaSubastasDTO);

        //se setea el comprador en misPujasViewController
        this.misPujasViewController.setComprador(miComprador);
        //se setean las Pujas en misPujasViewController
        ObservableList<PujaDTO> listaPujasDTO = FXCollections.observableArrayList();
        listaPujasDTO.addAll(mapperPuja.getPujasDTO(miComprador.getListaPujas()));
        this.misPujasViewController.setListaPujasDTO(listaPujasDTO);
    }

    @Override
    public void resetCuenta(String usuario) {
        this.miAnunciante = null;
        this.miComprador = null;
        this.anuncioSeleccionado = null;
        resetSeleccionAnuncio();

        //se setea la informacion para CuentaViewController
        this.miCuentaViewController.setAnunciante(null);
        this.miCuentaViewController.setComprador(null);

        //se setea la informacion para ProductoViewController
        this.productosViewController.setAnunciante(null);
        this.productosViewController.limpiarCamposProducto();
        //se setea la informacion para MisAnunciosViewController
        this.misAnunciosViewController.setAnunciante(null);
        this.misAnunciosViewController.limpiarCamposAnuncio();
        //se setea la informacion para MisPujasViewController
        this.misPujasViewController.setAnuncio(null);
        this.misPujasViewController.setComprador(null);
        this.misPujasViewController.limpiarInformacion();

        //se desautentica el usuario y se dehabilitan las pestaññas
        miSubasta.desAutenticarUsuario(usuario);
        this.ventanaPrincipalViewController.dehabilitarPestanias();
    }

    @Override
    public void resetCuenta() {
        //reset para el accion en la que se elimina una cuenta
        this.miAnunciante = null;
        this.miComprador = null;
        this.anuncioSeleccionado = null;

        //Se limpia la informacion contenida en las pestañas
        this.productosViewController.limpiarCamposProducto();
        this.misAnunciosViewController.limpiarCamposAnuncio();
        this.misPujasViewController.limpiarInformacion();

        resetSeleccionAnuncio();
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

    //Métodos para manejar los productos -------------------------------------------------------------------------------

    @Override
    public List<ProductoDTO> obtenerProductosAnunciante() {
        if (miAnunciante != null) return mapper.getProductosDTO(miAnunciante.getListaProductos());
        return new ArrayList<ProductoDTO>();
    }

    @Override
    public boolean agregarProducto(ProductoDTO productoDTO) {
        Producto p = mapper.productoDTOtoProducto(productoDTO);
        try {
            return miAnunciante.crearProducto(p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getImage(), p.getValorInicial(), p.getTipoProducto());
        } catch (ProductoException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean renovarProducto(String codigoProducto, ProductoDTO productoDTO) {
        Producto p = mapper.productoDTOtoProducto(productoDTO);
        try {
            return miAnunciante.actualizarProducto(codigoProducto, p);
        } catch (ProductoException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean expelerProducto(String codigo) {

        boolean aux = false;
        try {
            aux = miAnunciante.eliminarProducto(codigo);
        } catch (ProductoException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
        }
        return aux;
    }

    //Métodos para manejar los Anuncios -------------------------------------------------------------------------------

    @Override
    public ProductoDTO obtenerProductoDto(String codigoProducto) {
        Producto producto = miSubasta.obtenerProducto(codigoProducto);
        return mapper.productoToProductoDTO(producto);
    }

    @Override
    public Anuncio obtenerAnuncio(String codigoAnuncio) {
        return miSubasta.obtenerAnuncio(codigoAnuncio);
    }

    @Override
    public boolean actualizarAnuncio(String codigoAnuncio, AnuncioDTO anuncioDTO) {
        Anuncio a = mapperAnuncio.anuncioDTOtoAnuncio(anuncioDTO);
        try {
            boolean flag = miAnunciante.actualizarAnuncio(a.getCodigo(), a.getFechaInicio(), a.getFechaFinal(), a.getNombreAnunciante(), a.getProducto().getCodigo());
            refrescarTablaSubastas();
            return flag;
        } catch (AnuncioException | ProductoException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAnuncio(String codigo) {
        try {
            boolean flag = miAnunciante.eliminarAnuncio(codigo);
            refrescarTablaSubastas();
            return flag;
        } catch (AnuncioException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<PujaDTO> obtenerPujasDto(List<Puja> listaPujas) {
        return mapperPuja.getPujasDTO(listaPujas);
    }

    @Override
    public boolean agregarAnuncio(AnuncioDTO anuncioDTO) {
        Anuncio a = mapperAnuncio.anuncioDTOtoAnuncio(anuncioDTO);
        try {
            boolean flag = miAnunciante.crearAnuncio(a.getCodigo(), a.getFechaInicio(), a.getFechaFinal(), a.getNombreAnunciante(), a.getProducto().getCodigo());
            refrescarTablaSubastas();
            return flag;
        } catch (ProductoException | AnuncioException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
            return false;
        }
    }

    private List<AnuncioDTO> obtenerAnunciosAnunciante() {
        if (miAnunciante != null) return mapperAnuncio.getAnunciosDTO(miAnunciante.getListaAnuncios());
        return new ArrayList<AnuncioDTO>();
    }

    @Override
    public void refrescarTablaSubastas() {
        //se setean los anuncios en SubastasViewComtroller
        ObservableList<AnuncioDTO> listaSubastasDTO = FXCollections.observableArrayList();
        listaSubastasDTO.addAll(mapperAnuncio.getAnunciosDTO(miSubasta.getListaAnuncios()));
        this.subastasViewController.setListaAnunciosDTO(listaSubastasDTO);
    }

    //Métodos para manejar los la ventana de seleccion de anuncio -------------------------------------------------------------------------------

    @Override
    public void initAnuncioSeleccionado(AnuncioDTO anuncioSeleccionado) {
        this.anuncioSeleccionado = mapperAnuncio.anuncioDTOtoAnuncio(anuncioSeleccionado);
        this.misPujasViewController.setAnuncio(this.anuncioSeleccionado);

        //Se registra la accion en SubastasUQ_Log.txt
        String usuario = "";
        if (miAnunciante != null) usuario = miAnunciante.getUsuario();
        if (miComprador != null) usuario = miComprador.getUsuario();
        ModelFactoryController.registrarAccion(usuario, "selección de anuncio");
    }

    @Override
    public void resetSeleccionAnuncio() {
        this.subastasViewController.resetSeleccionAnuncio();
    }

    //Métodos para manejar los la ventana de MisPujas -------------------------------------------------------------------------------

    @Override
    public boolean agregarPuja(PujaDTO pujaDto) {
        Puja p = mapperPuja.PujaDTOtoPuja(pujaDto);
        try {
            return miComprador.realizarPuja(p.getAnuncio().getCodigo(), p.getValor(), p.getFecha());
        } catch (PujaException e) {
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);

            System.out.println(e.getMessage());
            return false;
        }
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos Flujo de Datos ---------------------------------------------------------------------------------------------------------------------------------

    //Registro de actividad (logger)--------------------------------------------------------------------------------------------------------------------------

    //Metodo para registrar las acciones del usuario
    public static void registrarAccion(String usuario, String accion) {
        LOGGER.log(Level.INFO, "Usuario " + usuario + " realizó la acción: " + accion);
    }

    //Metodo para registrar el ingreso del usuario
    public static void registrarIngresoUsuario(String usuario) {
        LOGGER.log(Level.INFO, "Ingreso al sistema: Usuario " + usuario + " ha ingresado.");
    }

    //Metodo para registrar las excepciones propias
    public static void registrarExcepcion(Exception e) {
        LOGGER.log(Level.WARNING, "Excepcion lanzada: " + e.getMessage());
    }

    //Metodos para escribir la información de la clase general Subasta (miSubasta) en archivos .txt -------------------------------------------------------------------------------------

    //Metodo para cargar y guardad la informacion de 1 objeto con CRUD "Producto"
    public static void writeBackupProduct(){
        BackupProducto.writeBackup(miSubasta.getListaProductos());
    }

    //Metodo para adicionar la informacion de 1 objeto con CRUD "Producto" a su txt "productos.txt"
    public static void appendToBackupProduct(ProductoDTO p){
        BackupProducto.appendToBackup(getInstance().mapper.productoDTOtoProducto(p));
    }

    //Metodo para cargar y guardad la informacion de 1 objeto con CRUD "Usuario"
    public static void writeBackupUser(){
        BackupUsuario.writeBackup(miSubasta.getListaUsuarios());
    }

    //Metodo para adicionar la informacion de 1 objeto con CRUD "Usuario" a su txt "usuarios.txt"
    public static void appendToBackupUser(Usuario u){
        BackupUsuario.appendToBackup(u);
    }

    //Metodo para cargar y guardad la informacion de 1 objeto con CRUD "Usuario"
    public static void writeBackupAdvertisement(){
        BackupAnuncio.writeBackup(miSubasta.getListaAnuncios());
    }

    //Metodo para adicionar la informacion de 1 objeto con CRUD "Usuario" a su txt "usuarios.txt"
    public static void appendToBackupAdvertisement(AnuncioDTO a){
        BackupAnuncio.appendToBackup(getInstance().mapperAnuncio.anuncioDTOtoAnuncio(a));
    }

    //Metodo para cargar y guardad la informacion de Transaccion "Puja"
    public static void writeBackupBid(){
        BackupPuja.writeBackup(miSubasta.getListaPujas());
    }

    //Metodo para adicionar la informacion de Transaccion "Pujas" a su txt "pujas_Transaccion.txt"
    public static void appendToBackupBid(PujaDTO p){
        BackupPuja.appendToBackup(getInstance().mapperPuja.PujaDTOtoPuja(p));
    }

    //Metodo para cargar y guardad la informacion de Transaccion "Compra"
    public static void writeBackupBuys(){
        BackupCompra.writeBackup(miSubasta.getListaCompras());
    }

    //Metodo para adicionar la informacion de Transaccion "Compra" a su txt "compras_Transaccion.txt"
    public static void appendToBackupBuys(Compra c){
        BackupCompra.appendToBackup(c);
    }

    //Metodos para serializar y deserializar la informacion de la clase global miSubastas ----------------------------------------------------------------------------------------------------------------------

    //guardarResourceBinario()
    public static void serializarBinario() {
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
        //Se almacenan los datos en archivos
        Persistencia.serializarBinario(RUTA_ARCHIVO_SUBASTAUQDAT, miSubasta);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    //cargarResourceBinario()
    public static void deserializarBinario(){
        if(Persistencia.deserializarBinario(RUTA_ARCHIVO_SUBASTAUQDAT) != null) {
            miSubasta = Persistencia.deserializarBinario(RUTA_ARCHIVO_SUBASTAUQDAT);
        }
    }

    //guardarResourceXML()
    public static void serializarXML() {
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
        //Se almacenan los datos en archivos
        Persistencia.serializarXML(RUTA_ARCHIVO_SUBASTAUQXML, miSubasta);
        Persistencia.serializarXML("C:/ArchivosSubastasUQ/SubastasUQ.xml", miSubasta);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    //cargarResourceXML()
    public static void deserializarXML(){
        if(Persistencia.deserializarXML(RUTA_ARCHIVO_SUBASTAUQXML) != null) {
            miSubasta = Persistencia.deserializarXML(RUTA_ARCHIVO_SUBASTAUQXML);
        }
    }

    //cargarDatosDesdeArchivos()
    public static void cargarDatosDesdeArchivos(){
        Persistencia.cargarDatosDesdeArchivos(miSubasta);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Metodos para exportar archivos .csv ------------------------------------------------------------------------------------------------------------------------------------------------------

    //Metodo para exportar los anuncios en formato .csv
    @Override
    public void convertAnunciosTxtToCsv(String outputFolderPath){
        Persistencia.convertAnunciosTxtToCsv(outputFolderPath);

        //Se registra la accion en SubastasUQ_Log.txt
        String usuario = "SubastasUQ";
        if (miAnunciante != null) usuario = miAnunciante.getUsuario();
        if (miComprador != null) usuario = miComprador.getUsuario();
        ModelFactoryController.registrarAccion(usuario, "Exportación CSV Anuncios");
    }

    //Metodo para exportar las compras en formato .csv
    @Override
    public void convertComprasTxtToCsv(String outputFolderPath){
        Persistencia.convertComprasTxtToCsv(outputFolderPath);

        //Se registra la accion en SubastasUQ_Log.txt
        String usuario = "SubastasUQ";
        if (miAnunciante != null) usuario = miAnunciante.getUsuario();
        if (miComprador != null) usuario = miComprador.getUsuario();
        ModelFactoryController.registrarAccion(usuario, "Exportación CSV Compras");
    }

    //Metodo para manejo de archivos en disco del PC
    public static void exportarArchivosEnPC(){

        //Rutas de acceso a archivos en el proyecto
        String RUTA_ANUNCIOSPROYECTO = "src/main/resources/persistencia/archivos/anuncios.txt";
        String RUTA_COMPRASPROYECTO = "src/main/resources/persistencia/archivos/compras_Transaccion.txt";
        String RUTA_PRODUCTOSPROYECTO = "src/main/resources/persistencia/archivos/productos.txt";
        String RUTA_PUJASPROYECTO = "src/main/resources/persistencia/archivos/pujas_Transaccion.txt";
        String RUTA_USUARIOSPROYECTO = "src/main/resources/persistencia/archivos/usuarios.txt";
        String RUTA_LOGPROYECTO = "src/main/resources/persistencia/log/SubastasUQ_Log.txt";

        //Rutas de acceso a archivos en el disco del pc
        String RUTA_ANUNCIOSPC = "C:/ArchivosSubastasUQ/archivos/anuncios.txt";
        String RUTA_COMPRASPC = "C:/ArchivosSubastasUQ/archivos/compras_Transaccion.txt";
        String RUTA_PRODUCTOSPC = "C:/ArchivosSubastasUQ/archivos/productos.txt";
        String RUTA_PUJASPC = "C:/ArchivosSubastasUQ/archivos/pujas_Transaccion.txt";
        String RUTA_USUARIOSPC = "C:/ArchivosSubastasUQ/archivos/usuarios.txt";
        String RUTA_LOGPC = "C:/ArchivosSubastasUQ/log/SubastasUQ_Log.txt";

        //Realizar copias de cada archivo en las direcciones del disco del PC
        Persistencia.copyFile(RUTA_ANUNCIOSPROYECTO, RUTA_ANUNCIOSPC);
        Persistencia.copyFile(RUTA_COMPRASPROYECTO, RUTA_COMPRASPC);
        Persistencia.copyFile(RUTA_PRODUCTOSPROYECTO, RUTA_PRODUCTOSPC);
        Persistencia.copyFile(RUTA_PUJASPROYECTO, RUTA_PUJASPC);
        Persistencia.copyFile(RUTA_USUARIOSPROYECTO, RUTA_USUARIOSPC);
        Persistencia.copyFile(RUTA_LOGPROYECTO, RUTA_LOGPC);

    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Funciones necesarias para el manejo de rabbirt
    @Override
    public void producirMensaje(String message) {
        String queue = QUEUE_PRODUCTOR;
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queue, false, false, false, null);
            channel.basicPublish("", queue, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Message Sent ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumirMensajes(){
        hiloServicioConsumer = new Thread(this);
        hiloServicioConsumer.start();
    }

    public void detenerConsumidor(){
        try {
            hiloServicioConsumer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        if(currentThread == hiloServicioConsumer){
            consumirXML();
        }
    }

    private void consumirXML() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_CONSUMIDOR, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido");
                //actualizarEstado(message);

                //Se carga la informacion XML en el archivo SubastasUQ.xml
                //Se escribe la informacion obtenida desde rabbitmq en el archivo (.xml)
                Persistencia.escribirArchivoXML(message, "src/main/resources/persistencia/SubastasUQ.xml");
                //Se carga la informacion del nuevo archivo SubastasUQ.xml
                CargarXMLThread cargarXMLThread = new CargarXMLThread();
                cargarXMLThread.start();
                try {
                    cargarXMLThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //Se carga la informacion del modelo en los respectivos archivos (.txt)
                WriteBackupObjectsThread objectsThread = new WriteBackupObjectsThread();
                objectsThread.start();

                //Se refresca la informacion de la interfaz de usuario
                //se setean los anuncios en SubastasViewController
                ObservableList<AnuncioDTO> listaSubastasDTO = FXCollections.observableArrayList();
                listaSubastasDTO.addAll(mapperAnuncio.getAnunciosDTO(miSubasta.getListaAnuncios()));
                this.subastasViewController.setListaAnunciosDTO(listaSubastasDTO);

                //Caso Anunciante autenticado: Se carga la informacion del anunciante
                if(this.miAnunciante!=null){
                    //se añaden los productos segun el anunciante
                    ObservableList<ProductoDTO> listaProductosDTO = FXCollections.observableArrayList();
                    listaProductosDTO.addAll(obtenerProductosAnunciante());
                    this.productosViewController.setListaProductosDTO(listaProductosDTO);

                    //se añaden los anuncios segun el anunciante
                    ObservableList<AnuncioDTO> listaAnunciosDTO = FXCollections.observableArrayList();
                    listaAnunciosDTO.addAll(obtenerAnunciosAnunciante());
                    this.misAnunciosViewController.setListaAnunciosDTO(listaAnunciosDTO);
                    //se añaden los productos segun el anunciante
                    this.misAnunciosViewController.setListaProductosDTO(listaProductosDTO);
                    //se añaden las pujas segun el anunciante
                    this.misAnunciosViewController.setListaPujasDTO(FXCollections.observableArrayList());
                }
                //Caso Comprador autenticado: Se carga la informacion del comprador
                if(this.miComprador!=null){
                    //se setean las Pujas en misPujasViewController
                    ObservableList<PujaDTO> listaPujasDTO = FXCollections.observableArrayList();
                    listaPujasDTO.addAll(mapperPuja.getPujasDTO(miComprador.getListaPujas()));
                    this.misPujasViewController.setListaPujasDTO(listaPujasDTO);
                }
            };
            while (true) {
                channel.basicConsume(QUEUE_CONSUMIDOR, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
