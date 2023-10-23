package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.MisAnunciosController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.SubastasController;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    @FXML
    private Button btnExportarAnuncios;

    @FXML
    private Button btnExportarCompras;

    @FXML
    void exportarAnunciosEvent(ActionEvent event) {
        String outputFolderPath = getFolderPath();
        subastasControllerService.convertAnunciosTxtToCsv(outputFolderPath);
    }

    @FXML
    void exportarComprasEvent(ActionEvent event) {
        String outputFolderPath = getFolderPath();
        subastasControllerService.convertComprasTxtToCsv(outputFolderPath);
    }

    //Metodo para obtener la direccion de una carpeta elegida por el usuario
    public static String getFolderPath() {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecciona una carpeta para guardar el archivo CSV");
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            return selectedDirectory.getAbsolutePath();
        } else {
            return null; // El usuario canceló la selección
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Invocación SubastasViewController");
        subastasControllerService = new SubastasController();
        subastasControllerService.initSubastasViewController(this);

        subastasControllerService.mfm.refrescarTablaSubastas();
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
        columnImagenProducto.setCellFactory(tc -> new TableCell<AnuncioDTO, String>() {
            private final Button button = new Button("Ver Imagen");

            {
                button.setOnAction((ActionEvent event) -> {
                    AnuncioDTO anuncio = getTableView().getItems().get(getIndex());
                    String imageUrl = anuncio.producto().getImage();
                    // Abre la URL en el navegador o realiza la acción requerida
                    // Verificar si Desktop es compatible (puede lanzar excepciones)
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();

                        try {
                            // Abre la URL en el navegador predeterminado o la aplicación asociada
                            desktop.browse(new URI(imageUrl));
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
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

    public void resetSeleccionAnuncio() {
        tableSubastas.getSelectionModel().clearSelection();
    }
}

