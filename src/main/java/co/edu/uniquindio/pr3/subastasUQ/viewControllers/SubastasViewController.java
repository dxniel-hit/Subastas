package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SubastasViewController {

    @FXML
    private Hyperlink hplIniciarSesion;

    @FXML
    private Hyperlink hplRegistrarse;

    @FXML
    private TableColumn<?, ?> tbcCategoriaSubasta;

    @FXML
    private TableColumn<?, ?> tbcDescripcionSubasta;

    @FXML
    private TableColumn<?, ?> tbcImagenSubasta;

    @FXML
    private TableColumn<?, ?> tbcLoteSubasta;

    @FXML
    private TableView<?> tbvSubastas;

}
