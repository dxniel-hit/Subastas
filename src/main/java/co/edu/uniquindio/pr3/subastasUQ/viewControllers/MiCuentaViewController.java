package co.edu.uniquindio.pr3.subastasUQ.viewControllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.MiCuentaController;
import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
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

public class MiCuentaViewController implements Initializable {

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
    private ComboBox<String> comboTipoUsuario;

    @FXML
    private TextField inputEmail;

    @FXML
    void actualizarInformacionEvent(ActionEvent event) {
        String nombres = inputNombres.getText();
        String apellidos = inputApellidos.getText();
        String identificacion = inputIdentificacion.getText();
        String edad = inputEdad.getText();
        String email = inputEmail.getText();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(comboTipoUsuario.getSelectionModel().getSelectedItem().toString());
        if (validarDatos(nombres, apellidos, identificacion, email, edad, tipoUsuario)) {
            if (tipoUsuario.equals(TipoUsuario.ANUNCIANTE)) {
                try {
                    boolean flag = miCuentaController.mfm.actualizarAnuciante(nombres, apellidos, identificacion, Integer.parseInt(edad), email);
                    mostrarMensaje("Proceso Exitoso", "Cuenta de anunciante actualizada", "La cuenta de anunciante fue actualizada correctamente", Alert.AlertType.INFORMATION);

                    //Se registra la accion en SubastasUQ_Log.txt
                    ModelFactoryController.registrarAccion(miAnunciante.getUsuario(), "actualizacion cuenta anunciante");

                    setMiAnucianteInformation();
                } catch (AnuncianteException e) {
                    mostrarMensaje("Error de Actualizacion", "No se puede actualizar la cuenta de anunciante", e.getMessage(), Alert.AlertType.WARNING);
                    //Se registra la excepcion en SubastasUQ_Log.txt
                    ModelFactoryController.registrarExcepcion(e);
                }
            }
            if (tipoUsuario.equals(TipoUsuario.COMPRADOR)) {
                try {
                    boolean flag = miCuentaController.mfm.actualizarComprador(nombres, apellidos, identificacion, Integer.parseInt(edad), email);
                    mostrarMensaje("Proceso Exitoso", "Cuenta de Comprador actualizada", "La cuenta de comprador fue actualizada correctamente", Alert.AlertType.INFORMATION);

                    //Se registra la accion en SubastasUQ_Log.txt
                    ModelFactoryController.registrarAccion(miComprador.getUsuario(), "actualizacion cuenta comprador");

                    setMiCompradorInformation();
                } catch (CompradorException e) {
                    mostrarMensaje("Error de Actualizacion", "No se puede actualizar la cuenta de comprador", e.getMessage(), Alert.AlertType.WARNING);
                    //Se registra la excepcion en SubastasUQ_Log.txt
                    ModelFactoryController.registrarExcepcion(e);
                }
            }
        }
    }

    @FXML
    void cambiarContraseniaEvent(ActionEvent event) {
        String identifiacion = inputIdentificacion.getText();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(comboTipoUsuario.getSelectionModel().getSelectedItem().toString());
        String nuevaContrasenia = inputContrasenia.getText();
        try {
            String mensaje = miCuentaController.mfm.cambiarContrasenia(identifiacion, tipoUsuario, nuevaContrasenia);
            mostrarMensaje("Proceso Exitoso", "Contraseña actualizada", "La contraseña fue actualizada correctamente", Alert.AlertType.INFORMATION);

            //Se registra la accion en SubastasUQ_Log.txt
            String usuario = "";
            if(miAnunciante!=null) usuario = miAnunciante.getUsuario();
            if(miComprador!=null) usuario = miComprador.getUsuario();
            ModelFactoryController.registrarAccion(usuario, "cambio de contraseña");

        } catch (CompradorException | AnuncianteException e) {
            mostrarMensaje("Error de Actualizacion", "No se puedo actualizar la contraseña", e.getMessage(), Alert.AlertType.WARNING);
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);
        }
    }

    @FXML
    void cambiarUsuarioEvent(ActionEvent event) {
        String identifiacion = inputIdentificacion.getText();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(comboTipoUsuario.getSelectionModel().getSelectedItem().toString());
        String nuevoUsuario = inputUsuario.getText();
        try {
            String mensaje = miCuentaController.mfm.cambiarUsuario(identifiacion, tipoUsuario, nuevoUsuario);
            mostrarMensaje("Proceso Exitoso", "Usuario actualizado", "El usuario fue actualizado correctamente", Alert.AlertType.INFORMATION);

            //Se registra la accion en SubastasUQ_Log.txt
            ModelFactoryController.registrarAccion(nuevoUsuario, "cambio de usuario");

        } catch (CompradorException | AnuncianteException e) {
            mostrarMensaje("Error de Actualizacion", "No se puedo actualizar el usuario", e.getMessage(), Alert.AlertType.WARNING);
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);
        } catch (UsuarioEnUsoException e) {
            mostrarMensaje("Error de Usuario", "Usuario en Uso", e.getMessage(), Alert.AlertType.WARNING);
            //Se registra la excepcion en SubastasUQ_Log.txt
            ModelFactoryController.registrarExcepcion(e);
        }
    }

    @FXML
    void cerrarSesionEvent(ActionEvent event) {
        //Se registra la accion en SubastasUQ_Log.txt
        String usuario = "";
        if(miAnunciante!=null) usuario = miAnunciante.getUsuario();
        if(miComprador!=null) usuario = miComprador.getUsuario();
        ModelFactoryController.registrarAccion(usuario, "cierre de sesión");

        resetCuenta();
        miCuentaController.mfm.resetCuenta(inputUsuario.getText());
        vaciarCasillas();
    }

    public void resetCuenta() {
        this.miAnunciante = null;
        this.miComprador = null;
    }

    @FXML
    void eliminarCuentaEvent(ActionEvent event) {
        String identificacion = inputIdentificacion.getText();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(comboTipoUsuario.getSelectionModel().getSelectedItem().toString());
        if (tipoUsuario.equals(TipoUsuario.ANUNCIANTE)) {
            try {
                boolean flag = miCuentaController.mfm.eliminarAnunciante(identificacion);
                mostrarMensaje("Proceso Exitoso", "Cuenta eliminada", "La cuenta fue eliminada correctamente", Alert.AlertType.INFORMATION);

                //Se registra la accion en SubastasUQ_Log.txt
                ModelFactoryController.registrarAccion(miAnunciante.getUsuario(), "eliminación de cuenta de anunciante");

                vaciarCasillas();
                miCuentaController.mfm.resetCuenta();
                resetCuenta();
            } catch (AnuncianteException e) {
                mostrarMensaje("Error de Eliminación", "No se puedo eliminar la cuenta", e.getMessage(), Alert.AlertType.WARNING);
                //Se registra la excepcion en SubastasUQ_Log.txt
                ModelFactoryController.registrarExcepcion(e);
            }
        }
        if (tipoUsuario.equals(TipoUsuario.COMPRADOR)) {
            try {
                boolean flag = miCuentaController.mfm.eliminarComprador(identificacion);
                mostrarMensaje("Proceso Exitoso", "Cuenta eliminada", "La cuenta fue eliminada correctamente", Alert.AlertType.INFORMATION);

                //Se registra la accion en SubastasUQ_Log.txt
                ModelFactoryController.registrarAccion(miComprador.getUsuario(), "eliminación de cuenta de comprador");

                vaciarCasillas();
                miCuentaController.mfm.resetCuenta();
                resetCuenta();
            } catch (CompradorException e) {
                mostrarMensaje("Error de Eliminación", "No se puedo eliminar la cuenta", e.getMessage(), Alert.AlertType.WARNING);
                //Se registra la excepcion en SubastasUQ_Log.txt
                ModelFactoryController.registrarExcepcion(e);
            }
        }
    }

    public void vaciarCasillas() {
        inputNombres.setText(null);
        inputApellidos.setText(null);
        inputIdentificacion.setText(null);
        inputEdad.setText(null);
        inputUsuario.setText(null);
        inputContrasenia.setText(null);
        inputEmail.setText(null);
        comboTipoUsuario.getSelectionModel().clearSelection();
    }

    private boolean validarDatos(String nombres, String apellidos, String identificacion, String correo, String edad, TipoUsuario tipoUsuario) {
        String mensaje = "";

        if (nombres == null || nombres.equals(""))
            mensaje += "El nombre es invalido \n";

        if (apellidos == null || apellidos.equals(""))
            mensaje += "Los apellidos son invalidos \n";

        if (identificacion == null || identificacion.equals(""))
            mensaje += "La identificacion es invalida \n";


        if (correo == null || correo.equals(""))
            mensaje += "El correo es invalido \n";

        if (!edad.matches("\\d+"))
            mensaje += "La edad debe ser un numero \n";

        if (tipoUsuario == null)
            mensaje += "El Tipo de Usuario es invalido \n";

        if (mensaje.equals("")) {
            return true;
        } else {
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

        if (miAnunciante != null) setMiAnucianteInformation();
        if (miComprador != null) setMiCompradorInformation();
        inputIdentificacion.setDisable(true);
        comboTipoUsuario.setDisable(true);
    }

    public void setMiAnucianteInformation() {
        inputNombres.setText(miAnunciante.getNombres());
        inputApellidos.setText(miAnunciante.getApellidos());
        inputIdentificacion.setText(miAnunciante.getIdentificacion());
        inputEdad.setText(String.valueOf(miAnunciante.getEdad()));
        inputUsuario.setText(miAnunciante.getUsuario());
        inputContrasenia.setText(miAnunciante.getContrasenia());
        inputEmail.setText(miAnunciante.getEmail());
        comboTipoUsuario.getSelectionModel().select(miAnunciante.getTipoUsuario().toString());
    }

    public void setMiCompradorInformation() {
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

