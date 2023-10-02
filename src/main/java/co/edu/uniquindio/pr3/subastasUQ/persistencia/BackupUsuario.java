package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BackupUsuario {

    public static void writeBackup(List<Usuario> listaUsuarios){
        File usuariosRespaldo = new File("src/main/resources/persistencia/archivos/usuarios.txt");
        try {
            FileWriter writer = new FileWriter(usuariosRespaldo); //Para escribir en el archivo

            for (Usuario u : listaUsuarios) {
                writer.write(
                        u.getSubastasQuindio().getNombre()+"@"+
                                "@"+u.getNombres() +"@"+
                                "@"+u.getApellidos() +"@"+
                                "@"+u.getIdentificacion() +"@"+
                                "@"+u.getEdad() +"@"+
                                "@"+u.getUsuario() +"@"+
                                "@"+u.getContrasenia() +"@"+
                                "@"+u.getEmail() +"@"+
                                "@"+u.getAutenticado() +"@"+
                                "@"+u.getTipoUsuario() +"\n"
                );
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static void appendToBackup(Usuario u) {
        try {
            // Create a File object with the desired file name and location
            File usuariosRespaldo = new File("src/main/resources/persistencia/archivos/usuarios.txt");

            // Create a FileWriter object to write to the file with the append parameter set to true
            FileWriter writer = new FileWriter(usuariosRespaldo, true);

            //add the user information
            writer.write(u.getSubastasQuindio().getNombre()+"@"+
                    "@"+u.getNombres() +"@"+
                    "@"+u.getApellidos() +"@"+
                    "@"+u.getIdentificacion() +"@"+
                    "@"+u.getEdad() +"@"+
                    "@"+u.getUsuario() +"@"+
                    "@"+u.getContrasenia() +"@"+
                    "@"+u.getEmail() +"@"+
                    "@"+u.getAutenticado() +"@"+
                    "@"+u.getTipoUsuario() +"\n");

            // Close the FileWriter object to save the changes
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

}
