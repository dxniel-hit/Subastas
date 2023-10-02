package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import java.io.*;
import java.util.logging.*;

public class RegistroAnuncio {

    public static final Logger RESPALDERANUNCIO = Logger.getLogger(RegistroAnuncio.class.getName());
    private static FileHandler archivo;

    public static void obtenerDatosAnuncio() {

        try {

            archivo = new FileHandler("src/main/resources/persistencia/log/anuncio.txt", false);
            archivo.setFormatter(new SimpleFormatter());
            RESPALDERANUNCIO.addHandler(archivo);

            RESPALDERANUNCIO.setUseParentHandlers(false);
        } catch(SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarDatosAnuncio() {
        if (archivo != null) {
            archivo.close();
        }
    }

}
