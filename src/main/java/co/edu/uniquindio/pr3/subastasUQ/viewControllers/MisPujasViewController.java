package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.MisAnunciosController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.MisPujasController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.ProductoDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Comprador;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class MisPujasViewController implements Initializable {

    MisPujasController misPujasControllerService;
    ObservableList<PujaDTO> listaPujasDTO = FXCollections.observableArrayList();
    PujaDTO pujaSeleccionada;
    Anuncio anuncioSeleccionado;
    Comprador comprador;
    String fecha;

    @FXML
    private TextField inputAnuncio;

    @FXML
    private TextField inputComprador;

    @FXML
    private TextField inputValor;

    @FXML
    private TextField inputNumPujasEnAnuncio;

    @FXML
    private Button btnPujas;

    @FXML
    private TableView<PujaDTO> tablePujas;

    @FXML
    private TableColumn<PujaDTO, String> columnAnuncio;

    @FXML
    private TableColumn<PujaDTO, String> columnComprador;

    @FXML
    private TableColumn<PujaDTO, String> columnValor;

    @FXML
    private Button btnLimpiar;

    @FXML
    void limpiarEvent(ActionEvent event) {
        limpiarInformacion();
    }

    public void limpiarInformacion(){
        this.anuncioSeleccionado = null;
        misPujasControllerService.resetSeleccionAnuncio();
        this.pujaSeleccionada = null;
        tablePujas.getSelectionModel().clearSelection();
        inputAnuncio.setText(null);
        inputValor.setText(null);
        inputNumPujasEnAnuncio.setText(null);
    }

    @FXML
    void pujarEvent(ActionEvent event) {
        agregarPuja();
    }

    private PujaDTO construirPujaDto() {
        return new PujaDTO(
                anuncioSeleccionado,
                comprador,
                Double.parseDouble(inputValor.getText()),
                obtenerFechaActual()
        );
    }

    private void agregarPuja() {
        //1. Se verifica haya un anuncio seleccionado
        if(anuncioSeleccionado!=null){
            //2. Validar la información
            if (validarDatos(inputAnuncio.getText(), inputValor.getText(), obtenerFechaActual())) {
                //3. Capturar los datos
                if (mostrarMensajeParaConfirmar("Puja", "Realizar Puja", "¿Está seguro de realizar la puja?", Alert.AlertType.CONFIRMATION)) {
                    PujaDTO PujaDto = construirPujaDto();
                    if (misPujasControllerService.agregarPuja(PujaDto)) {
                        listaPujasDTO.add(PujaDto);
                        tablePujas.setItems(listaPujasDTO);
                        mostrarMensaje("Notificación Puja", "Puja creada", "La puja se ha creado con éxito", Alert.AlertType.INFORMATION);

                        //Se registra la accion en SubastasUQ_Log.txt
                        ModelFactoryController.registrarAccion(comprador.getUsuario(), "realizar puja");

                        limpiarInformacion();
                    } else {
                        mostrarMensaje("Notificación Puja", "Puja no creada", "La puja no se ha creado con éxito", Alert.AlertType.ERROR);
                    }
                }
            } else {
                mostrarMensaje("Notificación Puja", "Puja no creada", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Puja", "Anuncio no seleccionado", "No se ha seleccionado ningun anuncio (Ventana 'Subastas')", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Invocación MisAnunciosViewController");
        misPujasControllerService = new MisPujasController();
        misPujasControllerService.initMisPujasViewController(this);

        initView();
    }

    public void initView() {
        initDatabinding();
        listenerSelection();
    }

    private void initDatabinding() {
        columnAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().anuncio().getCodigo()));
        columnComprador.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().comprador().getIdentificacion()));
        columnValor.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().valor())));
    }

    private void listenerSelection() {
        tablePujas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            pujaSeleccionada = newSelection;
            mostrarInformacionPuja(pujaSeleccionada);
        });
    }

    private void mostrarInformacionPuja(PujaDTO pujaSeleccionada) {
        if (pujaSeleccionada != null) {
            inputAnuncio.setText(pujaSeleccionada.anuncio().getCodigo());
            inputComprador.setText(pujaSeleccionada.comprador().getIdentificacion());
            inputValor.setText(String.valueOf(pujaSeleccionada.valor()));
            inputNumPujasEnAnuncio.setText(String.valueOf(comprador.devolverNumeroPujasEnAnuncio(pujaSeleccionada.anuncio().getCodigo())));
        }
    }

    public void setListaPujasDTO(ObservableList<PujaDTO> listaPujasDTO) {
        this.listaPujasDTO = listaPujasDTO;
        this.tablePujas.setItems(this.listaPujasDTO);
    }

    public void setComprador(Comprador miComprador) {
        this.comprador = miComprador;
        listaPujasDTO.removeAll();
        tablePujas.getItems().removeAll();

        if (miComprador != null) {
            inputComprador.setText(comprador.getIdentificacion());
            inputComprador.setDisable(true);
            inputAnuncio.setDisable(true);
            inputNumPujasEnAnuncio.setDisable(true);
        }
    }

    public void setAnuncio(Anuncio anuncioSeleccionado) {
        this.anuncioSeleccionado = anuncioSeleccionado;
        mostrarInformacionAnuncio();
    }

    public void mostrarInformacionAnuncio(){
        if(this.anuncioSeleccionado!=null){
            inputAnuncio.setText(this.anuncioSeleccionado.getCodigo());
            if(this.comprador != null) inputNumPujasEnAnuncio.setText(String.valueOf(comprador.devolverNumeroPujasEnAnuncio(this.anuncioSeleccionado.getCodigo())));
        }
    }

    private boolean validarDatos(String codigoAnuncio, String valor, String fecha) {
        String mensaje = "";

        if (codigoAnuncio == null || codigoAnuncio.equals(""))
            mensaje += "El codigo del anuncio es invalido \n";

        //regex watafa
        if (!valor.matches("\\d+"))
            mensaje += "El valor debe ser un numero \n";

        if (fecha == null || fecha.equals(""))
            mensaje += "La fecha es invalida \n";

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

    public boolean mostrarMensajeParaConfirmar(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }

    public String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return fechaActual.format(formatter);
    }
}


