package co.edu.uniquindio.pr3.subastasUQ.application;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastasUQ.hilos.*;
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
        CopiasRespaldoThread copiasRespaldoThread = new CopiasRespaldoThread();
        copiasRespaldoThread.start();

        //Se inicializa la informacion de los objetos en "objeto_xxx.txt"
        WriteBackupObjectsThread objectsThread = new WriteBackupObjectsThread();
        objectsThread.start();

        //Se inicializa el hilo consumidor de rabbitmq
        ModelFactoryController.getInstance().consumirMensajes();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws InterruptedException {
        // Este método se llama al finalizar la aplicación
        // Coloca aquí cualquier limpieza o acciones de cierre que necesites
        Log.cerrarLogger();

        //Se copian los archivos manipulados en el disco del PC
        ArchivosPcThread archivosPcThread = new ArchivosPcThread();
        archivosPcThread.start();
        archivosPcThread.join();

        //Se serializa la informacion ingresada durante la ejecucion
        //guardarResourceBinario()
        GuardarBinarioThread guardarbinarioThread = new GuardarBinarioThread();
        guardarbinarioThread.start();
        guardarbinarioThread.join();
        //guardarResourceXML()
        GuardarXMLThread guardarXMLThread = new GuardarXMLThread();
        guardarXMLThread.start();
        guardarXMLThread.join();

        //Se detiene el hilo consumidor
        ModelFactoryController.getInstance().detenerConsumidor();
    }
}
