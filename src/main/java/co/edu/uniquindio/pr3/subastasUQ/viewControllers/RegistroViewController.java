package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.RegistroController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.services.IRegistroControllerService;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroViewController implements Initializable {

    RegistroController registroController;

    @FXML
    private TextField inputNombres;

    @FXML
    private PasswordField inputContrasenia;

    @FXML
    private TextField inputIdentificacion;

    @FXML
    private TextField inputApellidos;

    @FXML
    private TextField inputEdad;

    @FXML
    private TextField inputUsuario;

    @FXML
    private TextField inputEmail;

    @FXML
    private ComboBox comboTipoUsuario;

    @FXML
    void registrarAction(ActionEvent event) {
        String nombres = inputNombres.getText();
        String apellidos = inputApellidos.getText();
        String identificacion = inputIdentificacion.getText();
        String edad = inputEdad.getText();
        String usuario = inputUsuario.getText();
        String contrasenia = inputContrasenia.getText();
        String email = inputEmail.getText();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(comboTipoUsuario.getSelectionModel().getSelectedItem().toString());
        if(validarDatos(nombres, apellidos, identificacion, usuario, contrasenia, email, edad, tipoUsuario)) {
            if(tipoUsuario.equals(TipoUsuario.ANUNCIANTE)){
                try {
                    boolean flag = registroController.mfm.crearAnunciante(nombres, apellidos, identificacion, Integer.parseInt(edad), usuario, contrasenia, email);
                    mostrarMensaje("Proceso Exitoso", "Cuenta de Anunciante creado", "El usuario para anunciante fue creado exitosamente", Alert.AlertType.INFORMATION);
                    vaciarCasillas();
                } catch (UsuarioEnUsoException e) {
                    mostrarMensaje("Error de Usuario", "Usuario en Uso", e.getMessage(), Alert.AlertType.WARNING);
                } catch (AnuncianteException e) {
                    mostrarMensaje("Error de Creacion", "No se puede crear la cuenta", e.getMessage(), Alert.AlertType.WARNING);
                }
            }
            if(tipoUsuario.equals(TipoUsuario.COMPRADOR)){
                try {
                    boolean flag = registroController.mfm.crearComprador(nombres, apellidos, identificacion, Integer.parseInt(edad), usuario, contrasenia, email);
                    mostrarMensaje("Proceso Exitoso", "Cuenta de Comprador creado", "El usuario para comprador fue creado exitosamente", Alert.AlertType.INFORMATION);
                    vaciarCasillas();
                } catch (UsuarioEnUsoException e) {
                    mostrarMensaje("Error de Usuario", "Usuario en Uso", e.getMessage(), Alert.AlertType.WARNING);
                } catch (CompradorException e) {
                    mostrarMensaje("Error de Creacion", "No se puede crear la cuenta", e.getMessage(), Alert.AlertType.WARNING);
                }
            }
        }
    }

    public void vaciarCasillas(){
        inputNombres.setText(null); inputApellidos.setText(null); inputIdentificacion.setText(null);
        inputEdad.setText(null); inputUsuario.setText(null); inputContrasenia.setText(null);
        inputEmail.setText(null); comboTipoUsuario.getSelectionModel().clearSelection();
    }

    private boolean validarDatos(String nombres, String apellidos, String identificacion, String usuario, String contrasenia, String correo, String edad, TipoUsuario tipoUsuario) {
        String mensaje = "";

        if(nombres == null || nombres.equals(""))
            mensaje += "El nombre es invalido \n";

        if(apellidos == null || apellidos.equals(""))
            mensaje += "Los apellidos son invalidos \n";

        if(identificacion == null || identificacion.equals(""))
            mensaje += "La identificacion es invalida \n";

        if(usuario == null || usuario.equals(""))
            mensaje += "El usuario es invalido \n";

        if(contrasenia == null || contrasenia.equals(""))
            mensaje += "La contrasenia es invalida \n";

        if(correo == null || correo.equals(""))
            mensaje += "El correo es invalido \n";

        if(!edad.matches("\\d+"))
            mensaje += "La edad debe ser un numero \n";

        if(tipoUsuario == null)
            mensaje += "El Tipo de Usuario es invalido \n";

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Información Usuario", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("ANUNCIANTE", "COMPRADOR");
        comboTipoUsuario.setItems(list);

        System.out.println("Invocación EmpleadoViewController");
        registroController = new RegistroController();
        registroController.mfm.initRegistroViewController(this);
    }
}
