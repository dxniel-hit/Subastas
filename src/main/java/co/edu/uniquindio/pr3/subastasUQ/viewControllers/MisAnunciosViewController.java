package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.MisAnunciosController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.ProductoController;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.ProductoDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anunciante;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MisAnunciosViewController implements Initializable {

    MisAnunciosController misAnunciosControllerService;
    ObservableList<AnuncioDTO> listaAnunciosDTO = FXCollections.observableArrayList();
    ObservableList<PujaDTO> listaPujasDTO = FXCollections.observableArrayList();
    ObservableList<ProductoDTO> listaProductosDTO = FXCollections.observableArrayList();
    ProductoDTO productoSeleccionado;
    AnuncioDTO anuncioSeleccionado;
    PujaDTO pujaSeleccionada;
    Anunciante anunciante;
    private String fechaInicio;
    private String fechaFinalizacion;

    @FXML
    private TextField inputCodigo;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private DatePicker dateFechaFinalizacion;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TextField inputNombreAnunciante;

    @FXML
    private TableView<ProductoDTO> tableProducto;

    @FXML
    private TableColumn<ProductoDTO, String> columnCodigoProducto;

    @FXML
    private TableColumn<ProductoDTO, String> columnNombreProducto;

    @FXML
    private TableColumn<ProductoDTO, String> columnValorInicialProducto;

    @FXML
    private TableColumn<ProductoDTO, String> columnTipoProductoProducto;

    @FXML
    private TableColumn<ProductoDTO, String> columnDescripcionProducto;

    @FXML
    private TableColumn<ProductoDTO, String> columnImagenProducto;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<AnuncioDTO> tableMisAnuncios;

    @FXML
    private TableColumn<AnuncioDTO, String> columnCodigoAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnInicioAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnFinalAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnNombreAnuncianteAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnProductoAnuncio;

    @FXML
    private TableView<PujaDTO> tablePujas;

    @FXML
    private TableColumn<PujaDTO, String> columnAnuncioPuja;

    @FXML
    private TableColumn<PujaDTO, String> columnCompradorPuja;

    @FXML
    private TableColumn<PujaDTO, String> columnValorPuja;

    @FXML
    void actualizarAnuncioEvent(ActionEvent event) {

    }

    @FXML
    void agregarAnuncioEvent(ActionEvent event) {
        crearProducto();
    }

    private void crearProducto() {
        //1. Validar la información
        if (validarDatos(inputCodigo.getText(), fechaInicio, fechaFinalizacion, inputNombreAnunciante.getText(), productoSeleccionado)){
            //2. Capturar los datos
            AnuncioDTO AnuncioDto = construirAnuncioDto();
            if (misAnunciosControllerService.agregarAnuncio(AnuncioDto)) {
                listaAnunciosDTO.add(AnuncioDto);
                tableMisAnuncios.setItems(listaAnunciosDTO);
                mostrarMensaje("Notificación Anuncio", "Anuncio creado", "El anuncio se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposAnuncio();
            } else {
                mostrarMensaje("Notificación Anuncio", "Anuncio no creado", "El anuncio no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación Anuncio", "Anuncio no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void eliminarAnuncioEvent(ActionEvent event) {

    }

    @FXML
    void getFechaFinalizacion(ActionEvent event) {
        try{
            LocalDate myDate = dateFechaFinalizacion.getValue();
            fechaFinalizacion = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }catch(Exception ignored){

        }
    }

    @FXML
    void getFechaInicio(ActionEvent event) {
        try{
            LocalDate myDate = dateFechaInicio.getValue();
            fechaInicio = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        catch (Exception ignored){

        }
    }

    @FXML
    void nuevoAnuncioEvent(ActionEvent event) {
        limpiarCamposAnuncio();
    }

    @FXML
    void venderAccion(ActionEvent event) {

    }

    private boolean validarDatos(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, ProductoDTO productoSeleccionado) {
        String mensaje = "";

        if (codigo == null || codigo.equals(""))
            mensaje += "El codigo es invalido \n";

        if (fechaInicio == null || fechaInicio.equals(""))
            mensaje += "La fecha de inicio es invalida \n";

        if (fechaFinal == null || fechaFinal.equals(""))
            mensaje += "La fecha de finalizacion es invalida \n";

        if (nombreAnunciante == null || nombreAnunciante.equals(""))
            mensaje += "El nombre del Anunciante es invalido \n";

        if (productoSeleccionado == null)
            mensaje += "No se ha seleccionado un producto \n";

        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Información Producto", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Invocación MisAnunciosViewController");
        misAnunciosControllerService = new MisAnunciosController();
        misAnunciosControllerService.initMisAnunciosViewController(this);

        initView();
    }

    private void initProductoDatabinding() {
        columnCodigoProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().codigo()));
        columnNombreProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().nombre()));
        columnValorInicialProducto.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().valorInicial())));
        columnTipoProductoProducto.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().tipoProducto())));
        columnDescripcionProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().descripcion()));
        columnImagenProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().image()));
    }

    private void initAnuncioDatabinding() {
        columnCodigoAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().codigo()));
        columnInicioAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().fechaInicio()));
        columnFinalAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().fechaFinal()));
        columnNombreAnuncianteAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().nombreAnunciante()));
        columnProductoAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().producto().getCodigo()));
    }

    private void initPujaDatabinding() {
        columnAnuncioPuja.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().anuncio().getCodigo()));
        columnCompradorPuja.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().comprador().getIdentificacion()));
        columnValorPuja.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().valor())));
    }

    private void productoListenerSelection() {
        tableProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
        });
    }

    private void pujaListenerSelection() {
        tablePujas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            pujaSeleccionada = newSelection;
        });
    }

    //¡Esto NO se toca!
    private void anuncioListenerSelection() {
        tableMisAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            anuncioSeleccionado = newSelection;
            inputCodigo.setText(anuncioSeleccionado.codigo());
            dateFechaInicio.setValue(LocalDate.parse(anuncioSeleccionado.fechaInicio(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            fechaInicio = anuncioSeleccionado.fechaInicio();
            dateFechaFinalizacion.setValue(LocalDate.parse(anuncioSeleccionado.fechaFinal(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            fechaFinalizacion = anuncioSeleccionado.fechaFinal();

            tableProducto.getSelectionModel().select(misAnunciosControllerService.obtenerProductoDto(anuncioSeleccionado.producto().getCodigo()));
            Anuncio a = misAnunciosControllerService.obtenerAnuncio(anuncioSeleccionado.codigo());
            listaPujasDTO.removeAll();
            listaPujasDTO.addAll(misAnunciosControllerService.obtenerPujasDto(a.getListaPujas()));
            tablePujas.getItems().removeAll();
            tablePujas.setItems(listaPujasDTO);
        });
    }

    public void initView() {
        initProductoDatabinding();
        initAnuncioDatabinding();
        initPujaDatabinding();
        productoListenerSelection();
        pujaListenerSelection();
        anuncioListenerSelection();
    }

    private AnuncioDTO construirAnuncioDto() {
        return new AnuncioDTO(
                inputCodigo.getText(),
                fechaInicio,
                fechaFinalizacion,
                this.anunciante.getNombres(),
                misAnunciosControllerService.poductoDTOtoPRoducto(productoSeleccionado)
        );
    }

    private void limpiarCamposAnuncio() {
        inputCodigo.setText(null);
        fechaInicio=""; dateFechaInicio.setValue(null);
        fechaFinalizacion=""; dateFechaFinalizacion.setValue(null);
        productoSeleccionado = null;
    }

    public void setAnunciante(Anunciante miAnunciante) {
        this.anunciante = miAnunciante;
        listaAnunciosDTO.removeAll();
        tableMisAnuncios.getItems().removeAll();
        listaProductosDTO.removeAll();
        tableProducto.getItems().removeAll();

        if(miAnunciante!=null){
            inputNombreAnunciante.setText(anunciante.getNombres());
            inputNombreAnunciante.setDisable(true);
        }
    }

    public void setListaAnunciosDTO(ObservableList<AnuncioDTO> listaAnunciosDTO) {
        this.listaAnunciosDTO = listaAnunciosDTO;
        this.tableMisAnuncios.setItems(this.listaAnunciosDTO);
    }

    public void setListaProductosDTO(ObservableList<ProductoDTO> listaProductosDTO) {
        this.listaProductosDTO = listaProductosDTO;
        this.tableProducto.setItems(this.listaProductosDTO);
    }
}

