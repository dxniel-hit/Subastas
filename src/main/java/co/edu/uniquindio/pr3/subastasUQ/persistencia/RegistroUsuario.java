package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import java.io.*;
import java.util.logging.*;

public class RegistroUsuario {

    public static final Logger RESPALDERUSUARIO = Logger.getLogger(RegistroUsuario.class.getName());
    private static FileHandler archivo;

    public static void obtenerDatosUsuario() {

        try {

            archivo = new FileHandler("src/main/resources/persistencia/log/usuario.txt", false);
            archivo.setFormatter(new SimpleFormatter());
            RESPALDERUSUARIO.addHandler(archivo);

            RESPALDERUSUARIO.setUseParentHandlers(false);
        } catch(SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarDatosUsuario() {
        if (archivo != null) {
            archivo.close();
        }
    }

}
