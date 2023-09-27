package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.MisAnunciosController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.SubastasController;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SubastasViewController implements Initializable {

    SubastasController subastasControllerService;
    ObservableList<AnuncioDTO> listaAnunciosDTO = FXCollections.observableArrayList();
    AnuncioDTO anuncioSeleccionado;

    @FXML
    private TableView<AnuncioDTO> tableSubastas;

    @FXML
    private TableColumn<AnuncioDTO, String> columnCodigoAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnInicioAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnFinalAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnNombreAnuncianteAnuncio;

    @FXML
    private TableColumn<AnuncioDTO, String> columnCodigoProducto;

    @FXML
    private TableColumn<AnuncioDTO, String> columnValorProducto;

    @FXML
    private TableColumn<AnuncioDTO, String> columnDescripcionProducto;

    @FXML
    private TableColumn<AnuncioDTO, String> columnImagenProducto;

    @FXML
    private Hyperlink hplRegistrarse;

    @FXML
    private Hyperlink hplIniciarSesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Invocación SubastasViewController");
        subastasControllerService = new SubastasController();
        subastasControllerService.initSubastasViewController(this);

        initView();
    }

    public void initView() {
        initDatabinding();
        listenerSelection();
    }

    private void initDatabinding() {
        columnCodigoAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().codigo()));
        columnInicioAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().fechaInicio()));
        columnFinalAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().fechaFinal()));
        columnNombreAnuncianteAnuncio.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().nombreAnunciante()));
        columnCodigoProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().producto().getCodigo()));
        columnValorProducto.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().producto().getValorInicial())));
        columnDescripcionProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().producto().getDescripcion()));
        columnImagenProducto.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().producto().getImage()));
    }

    private void listenerSelection() {
        tableSubastas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            anuncioSeleccionado = newSelection;
            subastasControllerService.initAnuncioSeleccionado(anuncioSeleccionado);
        });
    }

    public void setListaAnunciosDTO(ObservableList<AnuncioDTO> listaSubastasDTO) {
        this.listaAnunciosDTO = listaSubastasDTO;
        this.tableSubastas.setItems(this.listaAnunciosDTO);
    }
}

