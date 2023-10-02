package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

//Logaritmo ahuehue
public class Log {

    public static final Logger LOGGER = Logger.getLogger(Log.class.getName());
    private static FileHandler archivo;

    //Se configura en co.edu.uniquindio.pr3.subastasUQ.application.App
    public static void configurarLogger() {
        try {

            archivo = new FileHandler("src/main/resources/persistencia/log/SubastasUQ_Log.txt", false); // Reemplaza "ruta" con la ruta real
            archivo.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(archivo);

            // Aquí configuramos el logger para usar el FileHandler
            LOGGER.setUseParentHandlers(false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

    }

    //Se configura en co.edu.uniquindio.pr3.subastasUQ.application.App
    public static void cerrarLogger() {
        if (archivo != null) {
            archivo.close();
        }
    }

}
