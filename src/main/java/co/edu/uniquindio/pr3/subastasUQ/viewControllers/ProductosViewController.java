package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.*;
import java.util.*;

public class ProductosViewController implements Initializable {


    /**
     * Atributos de la clase
     */
    ProductoController productoControllerService;
    ObservableList<ProductoDTO> listaProductosDTO = FXCollections.observableArrayList();
    ProductoDTO productoSeleccionado;

    /**
     * Atributos de la interfaz
     */

    @FXML
    private Button bntAgregarNuevoProd;

    @FXML
    private Button bntAgregarProducto;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnCancelarNuevoProd;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button btnSeleccionarImagenProd;

    @FXML
    private ComboBox<?> cmbTipoProd;

    @FXML
    private Tab tabInfoProd;

    @FXML
    private Tab tabProductos;

    @FXML
    private TableColumn<ProductoDTO, String> tbcCategoriaProd;

    @FXML
    private TableColumn<ProductoDTO, String> tbcCodigoProd;

    @FXML
    private TableColumn<ProductoDTO, String> tbcDescripcionProd;

    @FXML
    private TableColumn<ProductoDTO, String> tbcExistenciasProd;

    @FXML
    private TableColumn<ProductoDTO, String> tbcImagenProd;

    @FXML
    private TableColumn<ProductoDTO, String> tbcNombreProd;

    @FXML
    private TableColumn<ProductoDTO, String> tbcPrecioProd;

    @FXML
    private TableView<ProductoDTO> tbvProductos;

    @FXML
    private TextArea txaDescripcionProducto;

    @FXML
    private TextField txfCodigoProd;

    @FXML
    private TextField txfNombreProd;

    @FXML
    private TextField txfValorInicialProd;

    /**
     * Métodos de la clase ------------------------------------------------------------------------------------------------
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productoControllerService = new ProductoController();
        initView();
    }

    private void initView() {

        initDatabinding();
        obtenerProductos();
        tbvProductos.getItems().clear();
        tbvProductos.setItems(listaProductosDTO);
        listenerSelection();
    }

    private void initDatabinding() {
        tbcCategoriaProd.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().tipoProducto())));
        tbcNombreProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().nombre()));
        tbcCodigoProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().codigo()));
        tbcDescripcionProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().descripcion()));
        tbcPrecioProd.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().valorInicial())));
    }

    private void obtenerProductos() {
        listaProductosDTO.addAll(productoControllerService.obtenerProductos());
    }

    private void listenerSelection() {

        tbvProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            //MostrarInfomacionProducto method to be added
        });
    }

    @FXML
    void actualizarProducto(ActionEvent event) {

    }

    @FXML
    void agregarProducto(ActionEvent event) {

    }

    @FXML
    void cancelarNuevoProd(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void irVentanaInfoProd(ActionEvent event) {

    }

    @FXML
    void seleccionarImagen(ActionEvent event) {

    }
}
