package co.edu.uniquindio.pr3.subastasUQ.application;

import co.edu.uniquindio.pr3.subastasUQ.persistencia.*;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaPrincipalView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        VentanaPrincipalViewController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();

        // Configurar el logger al inicio de la aplicación
        Log.configurarLogger();
        RegistroProducto.obtenerDatosProductos();
        RegistroAnuncio.obtenerDatosAnuncio();
        RegistroUsuario.obtenerDatosUsuario();
    }

    public static void main(String[] args) {
        launch();
    }


    /**
     * Que buen metodo jeje
     */
    @Override
    public void stop() throws Exception {

        // Este método se llama al finalizar la aplicación
        // Coloca aquí cualquier limpieza o acciones de cierre que necesites
        Log.cerrarLogger();
        RegistroProducto.cerrarDatosProductos();
        RegistroAnuncio.cerrarDatosAnuncio();
        RegistroUsuario.cerrarDatosUsuario();
        super.stop();
    }
}
