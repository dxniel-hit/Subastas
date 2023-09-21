package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.MiCuentaController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.RegistroController;
import co.edu.uniquindio.pr3.subastasUQ.model.Anunciante;
import co.edu.uniquindio.pr3.subastasUQ.model.Comprador;
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

public class MiCuentaViewController implements  Initializable{

    MiCuentaController miCuentaController;
    Anunciante miAnunciante;
    Comprador miComprador;

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
    private ComboBox comboTipoUsuario;

    @FXML
    private TextField inputEmail;

    @FXML
    void actualizarInformacionEvent(ActionEvent event) {

    }

    @FXML
    void cambiarContraseniaEvent(ActionEvent event) {

    }

    @FXML
    void cambiarUsuarioEvent(ActionEvent event) {

    }

    @FXML
    void cerrarSesionEvent(ActionEvent event) {

    }

    @FXML
    void eliminarCuentaEvent(ActionEvent event) {

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
        miCuentaController = new MiCuentaController();
        miCuentaController.mfm.initMiCuentaViewController(this);

        if(miAnunciante!=null) setMiAnucianteInformation();
        if(miComprador!=null)setMiCompradorInformation();
    }

    public void setMiAnucianteInformation(){
        inputNombres.setText(miAnunciante.getNombres());
        inputApellidos.setText(miAnunciante.getApellidos());
        inputIdentificacion.setText(miAnunciante.getIdentificacion());
        inputEdad.setText(String.valueOf(miAnunciante.getEdad()));
        inputUsuario.setText(miAnunciante.getUsuario());
        inputContrasenia.setText(miAnunciante.getContrasenia());
        inputEmail.setText(miAnunciante.getEmail());
        comboTipoUsuario.getSelectionModel().select(miAnunciante.getTipoUsuario().toString());
    }

    public void setMiCompradorInformation(){
        inputNombres.setText(miComprador.getNombres());
        inputApellidos.setText(miComprador.getApellidos());
        inputIdentificacion.setText(miComprador.getIdentificacion());
        inputEdad.setText(String.valueOf(miComprador.getEdad()));
        inputUsuario.setText(miComprador.getUsuario());
        inputContrasenia.setText(miComprador.getContrasenia());
        inputEmail.setText(miComprador.getEmail());
        comboTipoUsuario.getSelectionModel().select(miComprador.getTipoUsuario().toString());
    }

    public void setAnunciante(Anunciante miAnunciante) {
        this.miAnunciante = miAnunciante;
        this.miComprador = null;
    }

    public void setComprador(Comprador miComprador) {
        this.miAnunciante = null;
        this.miComprador = miComprador;
    }
}

