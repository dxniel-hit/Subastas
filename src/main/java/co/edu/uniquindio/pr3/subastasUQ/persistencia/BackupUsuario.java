package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Subasta;
import co.edu.uniquindio.pr3.subastasUQ.model.Usuario;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

import java.io.*;
import java.util.ArrayList;
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

    public static List<Usuario> readBackup(Subasta subasta) {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        try {
            File archivo = new File("src/main/resources/persistencia/archivos/usuarios.txt");
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("@@");
                if (partes.length == 10) { // Verificar que la línea tenga el formato esperado
                    String nombreSubastaQuindio = partes[0];
                    String nombres = partes[1];
                    String apellidos = partes[2];
                    String identificacion = partes[3];
                    int edad = Integer.parseInt(partes[4]);
                    String usuario = partes[5];
                    String contrasenia = partes[6];
                    String email = partes[7];
                    boolean autenticado = Boolean.parseBoolean(partes[8]);
                    TipoUsuario tipoUsuario = TipoUsuario.valueOf(partes[9]);

                    // Crear un usuario y agregarlo a la lista
                    Usuario u = new Usuario(nombres, apellidos, identificacion, edad, subasta, usuario, contrasenia, email, autenticado, tipoUsuario);
                    listaUsuarios.add(u);
                } else {
                    // La línea no tiene el formato esperado, puedes manejarlo según tus necesidades
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
            }

            lector.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaUsuarios;
    }

}
