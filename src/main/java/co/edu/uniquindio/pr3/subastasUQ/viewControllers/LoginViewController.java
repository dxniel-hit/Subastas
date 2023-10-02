package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.LoginController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastasUQ.model.Anunciante;
import co.edu.uniquindio.pr3.subastasUQ.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    LoginController loginController;
    Anunciante miAnunciante;
    Comprador miComprador;


    @FXML
    private ImageView imageLogin;

    @FXML
    private TextField inputUsuario;

    @FXML
    private PasswordField inputContrasenia;

    @FXML
    void ingresarAction(ActionEvent event) {
        int pos = loginController.mfm.encontrarPosUsuario(inputUsuario.getText());
        if(pos != -1) {
            if(inputContrasenia.getText().equals(loginController.mfm.obtenerUsuario(pos).getContrasenia())) {
                String identificacion = loginController.mfm.obtenerUsuario(pos).getIdentificacion();
                miAnunciante = loginController.mfm.obtenerAnunciante(identificacion);
                miComprador = loginController.mfm.obtenerComprador(identificacion);
                if(miAnunciante!=null) {
                    vaciarCasillas();
                    loginController.mfm.setMiAnunciante(miAnunciante);
                    loginController.mfm.habilitarPestaniasAnunciante();
                    ModelFactoryController.registrarIngresoUsuario(miAnunciante.getUsuario());
                    //Se registra la informacion de los usuarios en usuarios.txt
                    ModelFactoryController.writeBackupUser();
                }
                if(miComprador!=null) {
                    vaciarCasillas();
                    loginController.mfm.setMiComprador(miComprador);
                    loginController.mfm.habilitarPestaniasComprador();
                    ModelFactoryController.registrarIngresoUsuario(miComprador.getUsuario());
                    //Se registra la informacion de los usuarios en usuarios.txt
                    ModelFactoryController.writeBackupUser();
                }
            }
            else {
                mostrarMensaje("Mensaje Informativo", "Contraseña incorrecta", "La contraseña ingresada es incorrecta", Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje("Mensaje Informativo", "Usuario no registrado", "El usuario no se encuentra registrado", Alert.AlertType.WARNING);
        }
    }

    public void vaciarCasillas(){
        inputUsuario.setText(null);
        inputContrasenia.setText(null);
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean validarDatos(String usuario, String contrasenia) {
        String mensaje = "";

        if(usuario == null || usuario.equals(""))
            mensaje += "El usuario es invalido \n";

        if(contrasenia == null || contrasenia.equals(""))
            mensaje += "La contraseña es invalida \n";

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Información Usuario", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Invocación EmpleadoViewController");
        loginController = new LoginController();
        loginController.mfm.initLoginViewController(this);
    }
}

