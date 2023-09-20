package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AnunciosViewController {

    @FXML
    private TableView<?> anunciosTable;

    @FXML
    private TableColumn<?, ?> codigoColumn;

    @FXML
    private TableColumn<?, ?> fechaInicioColumn;

    @FXML
    private TableColumn<?, ?> fechaFinalColumn;

    @FXML
    private TableColumn<?, ?> nombreAnuncianteColumn;

    @FXML
    private TableColumn<?, ?> productoColumn;

    @FXML
    private TextField inputCodigo;

    @FXML
    private DatePicker fechaInicioDate;

    @FXML
    private DatePicker fechaFinalDate;

    @FXML
    private TextField inputNombreAnunciante;

    @FXML
    private TextField inputCodigoProducto;

    @FXML
    private TextField inputAnuncio;

    @FXML
    private TextField inputValorInicial;

    @FXML
    private TextField inputIdentficacionComprador;

    @FXML
    private TextField inputValorPuja;

    @FXML
    void realizarPujaAction(ActionEvent event) {

    }

    @FXML
    void seleccionarAnuncio(MouseEvent event) {

    }

}

