package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import java.io.*;
import java.util.logging.*;

public class RegistroProducto {

    public static final Logger RESPALDERPRODUCTO = Logger.getLogger(RegistroProducto.class.getName());
    private static FileHandler archivo;

    public static void obtenerDatosProductos() {

        try {

            archivo = new FileHandler("src/main/resources/persistencia/log/producto.txt", false);
            archivo.setFormatter(new SimpleFormatter());
            RESPALDERPRODUCTO.addHandler(archivo);

            RESPALDERPRODUCTO.setUseParentHandlers(false);
        } catch(SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarDatosProductos() {
        if (archivo != null) {
            archivo.close();
        }
    }

}
