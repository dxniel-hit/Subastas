package co.edu.uniquindio.pr3.subastasUQ.viewControllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SubastasQuindioViewController {

    @FXML
    private Button ButtonEditarPerfil;

    @FXML
    private Button ButtonEditarPerfil1;

    @FXML
    private ImageView ImageViewFotoPerfil;

    @FXML
    private ImageView ImageViewPortada;

    @FXML
    private Label LabelNombre;

    @FXML
    private Label LabelNombre1;

    @FXML
    private Label LabelNombre11;

    @FXML
    private Label LabelNombre111;

    @FXML
    private Label LabelNombre112;



    //Atributos
    Stage stage;

    //Getters y setters

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}

