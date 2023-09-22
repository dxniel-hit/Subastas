package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ProductosViewController {

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
    private ComboBox<?> comboTipoProducto;

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

    public static ArrayList<Producto> getProductos(){
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
    void nuevoProductoEvent(ActionEvent event) {

    }

    @FXML
    void refrescarAtributosTipoProducto(ActionEvent event) {

    }

    @FXML
    void seleccionarImagenAction(ActionEvent event) {

    }

}



