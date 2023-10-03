package co.edu.uniquindio.pr3.subastasUQ.application;

import co.edu.uniquindio.pr3.subastasUQ.controllers.*;
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

        //Se realiza la copia de respaldo para los archivos
        Persistencia.realizarCopiasRespaldo();

        //Se inicializa la informacion de los objetos en "objeto_xxx.txt"
        ModelFactoryController.writeBackupProduct();
        ModelFactoryController.writeBackupUser();
        ModelFactoryController.writeBackupAdvertisement();
        ModelFactoryController.writeBackupBid();
        ModelFactoryController.writeBackupBuys();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        // Este método se llama al finalizar la aplicación
        // Coloca aquí cualquier limpieza o acciones de cierre que necesites
        Log.cerrarLogger();

        //Se serializa la informacion ingresada durante la ejecucion
        //guardarResourceBinario()
        ModelFactoryController.serializarBinario();
        //guardarResourceXML()
        ModelFactoryController.serializarXML();
    }
}
