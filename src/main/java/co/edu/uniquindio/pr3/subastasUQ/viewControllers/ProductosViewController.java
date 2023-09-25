package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.*;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.Anunciante;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.*;
import java.util.*;

public class ProductosViewController implements Initializable {


    /**
     * Atributos de la clase
     */
    ProductoController productoControllerService;
    ObservableList<ProductoDTO> listaProductosDTO = FXCollections.observableArrayList();
    ProductoDTO productoSeleccionado;
    Anunciante anunciante;
    String imagenProducto = "";

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
    private ComboBox<String> cmbTipoProd;

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

    @FXML
    private ImageView imageViewProducto;

    /**
     * Métodos de la clase ------------------------------------------------------------------------------------------------
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Invocación EmpleadoViewController");
        productoControllerService = new ProductoController();
        productoControllerService.mfm.initProductosViewControlles(this);

        initView();
        ObservableList<String> list = FXCollections.observableArrayList("TECNOLOGIA", "HOGAR", "DEPORTES", "VEHICULOS", "BIEN_RAIZ");
        cmbTipoProd.setItems(list);
    }

    public void setAnunciante(Anunciante anunciante){
        this.anunciante = anunciante;
        listaProductosDTO.removeAll();
        if(this.anunciante!=null) listaProductosDTO.addAll(obtenerProductos());
        System.out.println(listaProductosDTO);
    }

    public void initView() {
        initDatabinding();
        tbvProductos.getItems().removeAll();
        tbvProductos.setItems(listaProductosDTO);
        listenerSelection();
    }

    private void initDatabinding() {
        tbcImagenProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().image()));
        tbcCategoriaProd.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().tipoProducto())));
        tbcNombreProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().nombre()));
        tbcCodigoProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().codigo()));
        tbcDescripcionProd.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().descripcion()));
        tbcPrecioProd.setCellValueFactory(celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().valorInicial())));
    }

    private List<ProductoDTO> obtenerProductos() {
        return productoControllerService.mfm.obtenerProductosAnunciante();
    }

    private void listenerSelection() {

        tbvProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            //MostrarInfomacionProducto method to be added
        });
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean validarDatos(String codigo, String nombre, String descripcion, String image, String valorIncial, TipoProducto tipoProducto) {
        String mensaje = "";

        if(codigo == null || codigo.equals(""))
            mensaje += "El codigo es invalido \n";

        if(nombre == null || nombre.equals(""))
            mensaje += "El nombre es invalido \n";

        if(descripcion == null || descripcion.equals(""))
            mensaje += "La descpripcion es invalida \n";

        if(image == null || image.equals(""))
            mensaje += "La imagen es invalida \n";

        //regex watafa
        if(!valorIncial.matches("\\d+"))
            mensaje += "El valor inicial debe ser un numero \n";

        if(tipoProducto == null)
            mensaje += "El Tipo de Producto es invalido \n";

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Información Producto", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private ProductoDTO construirProductoDto() {
        return new ProductoDTO(
                txfCodigoProd.getText(),
                txfNombreProd.getText(),
                txaDescripcionProducto.getText(),
                this.imagenProducto,
                Double.parseDouble(txfValorInicialProd.getText()),
                TipoProducto.valueOf(cmbTipoProd.getSelectionModel().getSelectedItem()),
                false
        );
    }

    @FXML
    void actualizarProducto(ActionEvent event) {

    }

    @FXML
    void agregarProducto(ActionEvent event) {
        crearProducto();
    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void irVentanaInfoProd(ActionEvent event) {

    }

    @FXML
    void seleccionarImagen(ActionEvent event) {
        //Se crea el dialogo para seleccionar los archivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo PNG", "*.png"),
                new FileChooser.ExtensionFilter("Archivo JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("Archivo BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("Archivo GIF", "*.gif"));

        //Se abre el dialogo para seleccionar los archivos
        File file = fileChooser.showOpenDialog(null);

        //Se verifica que el archivo seleccionado sea una imagen
        if(file.isFile() && (file.getName().contains(".jpg") || file.getName().contains(".png") ||
                file.getName().contains(".bmp") || file.getName().contains(".gif"))) {

            try {
                String imageURL = file.toURI().toURL().toString();

                //Se agrega la imagen a las imagenes del vehiculo
                this.imagenProducto = imageURL;
                mostrarMensaje("Mensaje Informativo", "Imagen Producto", "Imagen seleccionada exitosamente", Alert.AlertType.INFORMATION);

                //Se carga la vista previa de la imagen
                Image imgLoad = new Image(imageURL);
                imageViewProducto.setImage(imgLoad);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        else {
            mostrarMensaje("Mensaje Informativo", "Imagen producto", "El formato del archivo no es una imagen", Alert.AlertType.WARNING);
        }
    }

    private void crearProducto() {
        //1. Capturar los datos
        ProductoDTO productoDto = construirProductoDto();
        //2. Validar la información
        if(validarDatos(txfCodigoProd.getText(), txfNombreProd.getText(), txaDescripcionProducto.getText(), imagenProducto, txfValorInicialProd.getText(), TipoProducto.valueOf(cmbTipoProd.getSelectionModel().getSelectedItem().toString()))){
            if(productoControllerService.agregarProducto(productoDto)){
                listaProductosDTO.add(productoDto);
                mostrarMensaje("Notificación producto", "Producto creado", "El producto se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            }else{
                mostrarMensaje("Notificación producto", "Producto no creado", "El producto no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación producto", "Producto no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void limpiarCamposProducto() {
        txfNombreProd.setText(null);
        txfCodigoProd.setText(null);
        txaDescripcionProducto.setText(null);
        imagenProducto = "";
        imageViewProducto.setImage(null);
        txfValorInicialProd.setText(null);
        cmbTipoProd.getSelectionModel().clearSelection();
    }

}
