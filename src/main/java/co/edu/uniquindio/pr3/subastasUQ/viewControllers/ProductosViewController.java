package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ProductoController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.RegistroController;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductosViewController implements Initializable {

    ProductoController productoController;

    @FXML
    private ListView<?> ListViewListaProductos;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private ComboBox<String> comboTipoProducto;

    @FXML
    private ImageView imageViewProducto;

    @FXML
    private TextField inputCodigo;

    @FXML
    private TextArea inputDescripcion;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputValorInicial;

    //Atributos de la clase

    private static ArrayList<Producto> productos;


    //Métodos de la clase

    public static ArrayList<Producto> getProductos() {
        return productos;
    }

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
    void nuevoProductoEvent(ActionEvent event) throws ProductoException {

        if (formularioProductoIsCompleto()) {

            String codigo = inputCodigo.getText();
            Double valorInicial = Double.parseDouble(inputValorInicial.getText());
            String descripcion = inputDescripcion.getText();
            String nombre = inputNombre.getText();
            Image image = imageViewProducto.getImage();
            TipoProducto tipoProducto = TipoProducto.valueOf(comboTipoProducto.getSelectionModel().getSelectedItem());

            try {
                Producto p = productoController.mfm.crearProducto(codigo,nombre,descripcion,image,valorInicial,tipoProducto);
            } catch (ProductoException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void refrescarAtributosTipoProducto(ActionEvent event) {

    }

    @FXML
    void seleccionarImagenAction(ActionEvent event) {

    }


    //Métodos no inyectados de la clase, probablemente sean removidos de acá

    private boolean formularioProductoIsCompleto() {

        boolean inputCodigoVacio = !inputCodigo.getText().trim().isEmpty();
        boolean inputValorInicialVacio = !inputValorInicial.getText().trim().isEmpty();
        boolean inputDescripcionVacio = !inputDescripcion.getText().trim().isEmpty();
        boolean inputNombreVacio = !inputNombre.getText().trim().isEmpty();
        boolean comboTipoVacio = comboTipoProducto.getValue() != null;

        return inputCodigoVacio && inputDescripcionVacio && inputValorInicialVacio && inputNombreVacio && inputCodigoVacio && comboTipoVacio;
    }

    //    tecnología, hogar, deportes, vehículos y bien raíz.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> list = FXCollections.observableArrayList("Bien Raíz", "Deporte", "Hogar", "Tecnológico", "Vehículo");
        comboTipoProducto.setItems(list);
        System.out.println("Invocación ProductoViewController");

        productoController = new ProductoController();
        productoController.mfm.initProductosViewController(this);
    }
}



