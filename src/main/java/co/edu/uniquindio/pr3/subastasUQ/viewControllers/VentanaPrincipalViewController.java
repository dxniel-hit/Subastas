package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import javafx.fxml.*;
import javafx.scene.control.Tab;
import javafx.stage.*;

import java.net.*;
import java.util.*;

public class VentanaPrincipalViewController implements Initializable {


    VentanaPrincipalController ventanaPrincipalController;

    //Atributos
    Stage stage;
    public Stage getStage() {
        return stage;
    }

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



    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //Metodo par deshabilitar las pestañas para sesión no iniciada
    public void dehabilitarPestanias(){
        inicioTab.setDisable(false);
        productosTab.setDisable(true);
        misAnunciosTab.setDisable(true);
        misPujasTab.setDisable(true);
        miCuentaTab.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dehabilitarPestanias();
        ventanaPrincipalController = new VentanaPrincipalController();
        ventanaPrincipalController.mfm.initVentanaPrincipalViewController(this);
    }

    public void habilitarPestaniasAnunciante() {
        inicioTab.setDisable(true);
        productosTab.setDisable(false);
        misAnunciosTab.setDisable(false);
        miCuentaTab.setDisable(false);
    }

    public void habilitarPestaniasComprador() {
        inicioTab.setDisable(true);
        misPujasTab.setDisable(false);
        miCuentaTab.setDisable(false);
    }
}
