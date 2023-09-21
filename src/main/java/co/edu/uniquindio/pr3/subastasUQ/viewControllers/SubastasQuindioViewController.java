package co.edu.uniquindio.pr3.subastasUQ.viewControllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SubastasQuindioViewController implements Initializable{

    //Atributos
    Stage stage;

    @FXML
    private Tab inicioTab;

    @FXML
    private Tab anunciosTab;

    @FXML
    private Tab productosTab;

    @FXML
    private Tab misAnunciosTab;

    @FXML
    private Tab misPujasTab;

    @FXML
    private Tab miCuentaTab;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //Metodo par deshabilitar las pestañas para sesión no iniciada
    public void dehabilitarPestanias(){
        productosTab.setDisable(true);
        misAnunciosTab.setDisable(true);
        misPujasTab.setDisable(true);
        miCuentaTab.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dehabilitarPestanias();
    }

}

