package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class NuevoLoginViewController implements Initializable {

    @FXML
    private Button ButtonIniciarSesion;

    @FXML
    private Hyperlink HyperlinkContraseniaOlvido;

    @FXML
    private ImageView ImageVentanaInicio;

    @FXML
    private TextField TFContrasenia;

    @FXML
    private TextField TFNombreUsuario;

    @FXML
    void contraseniaOlvidada(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) {

    }

    @Override
    public void initialize(URL Location, ResourceBundle resources) {

        Image img = new Image(getClass().getResource("/images/auction.png").toExternalForm());
        ImageVentanaInicio.setImage(img);
    }

}
