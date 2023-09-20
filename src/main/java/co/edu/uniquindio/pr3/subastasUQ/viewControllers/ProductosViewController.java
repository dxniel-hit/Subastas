package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductosViewController {

    @FXML
    private TextField inputCodigo;

    @FXML
    private TextField inputNombre;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private ComboBox<?> comboTipoProducto;

    @FXML
    private TextArea inputDescripcion;

    @FXML
    private TextField inputValorInicial;

    @FXML
    private ImageView imageViewProducto;

    @FXML
    private TableView<?> tableProducto;

    @FXML
    private TableColumn<?, ?> columnCodigo;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnValorInicial;

    @FXML
    private TableColumn<?, ?> columnTipoProducto;

    @FXML
    private TableColumn<?, ?> columnDescripcion;

    @FXML
    private TableColumn<?, ?> columnImagen;

    @FXML
    private Button btnEliminar;

    @FXML
    void actualizarProductoEvent(ActionEvent event) {

    }

    @FXML
    void agregarProductoEvent(ActionEvent event) {

    }

    @FXML
    void eliminarProductoEvent(ActionEvent event) {

    }

    @FXML
    void nuevoProductoEvent(ActionEvent event) {

    }

    @FXML
    void refrescarAtributosTipoProducto(ActionEvent event) {

    }

    @FXML
    void seleccionarImagenAction(ActionEvent event) {

    }

    @FXML
    void seleccionarProductoEvent(MouseEvent event) {

    }

}



