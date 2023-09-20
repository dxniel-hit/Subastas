package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MisPujasViewController {

    @FXML
    private TextField inputAnuncio;

    @FXML
    private TextField inputComprador;

    @FXML
    private TextField inputValor;

    @FXML
    private TextField inputNumPujasEnAnuncio;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<?> tablePujas;

    @FXML
    private TableColumn<?, ?> columnAnuncio;

    @FXML
    private TableColumn<?, ?> columnComprador;

    @FXML
    private TableColumn<?, ?> columnValor;

    @FXML
    void eliminarPujaEvent(ActionEvent event) {

    }

    @FXML
    void seleccionarPujaEvent(MouseEvent event) {

    }

}
