package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;
import co.edu.uniquindio.pr3.subastasUQ.model.Subasta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackupPuja {

    public static void writeBackup(List<Puja> listaPujas){
        File pujasRespaldo = new File("src/main/resources/persistencia/archivos/pujas_Transaccion.txt");
        try {
            FileWriter writer = new FileWriter(pujasRespaldo); //Para escribir en el archivo

            for (Puja p : listaPujas) {
                writer.write(
                        p.getAnuncio()+"@"+
                                "@"+p.getComprador().getIdentificacion() +"@"+
                                "@"+p.getValor() +"@"+
                                "@"+p.getFecha() +"\n"
                );
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static void appendToBackup(Puja p) {
        try {
            // Create a File object with the desired file name and location
            File pujasRespaldo = new File("src/main/resources/persistencia/archivos/pujas_Transaccion.txt");

            // Create a FileWriter object to write to the file with the append parameter set to true
            FileWriter writer = new FileWriter(pujasRespaldo, true);

            //add the product information
            writer.write(p.getAnuncio()+"@"+
                    "@"+p.getComprador().getIdentificacion() +"@"+
                    "@"+p.getValor() +"@"+
                    "@"+p.getFecha() +"\n");

            // Close the FileWriter object to save the changes
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static List<Puja> readBackup(Subasta subasta) {
        List<Puja> listaPujas = new ArrayList<Puja>();

        try {
            File archivo = new File("src/main/resources/persistencia/archivos/pujas_Transaccion.txt");
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("@@");
                if (partes.length == 4) { // Verificar que la línea tenga el formato esperado
                    String anuncio = partes[0];
                    String identificacionComprador = partes[1];
                    double valor = Double.parseDouble(partes[2]);
                    String fechaStr = partes[3];

                    // Crear una puja y agregarla a la lista
                    Puja puja = new Puja(subasta.obtenerAnuncio(obtenerCodigoAnuncioDesdeCadena(anuncio)), subasta.obtenerComprador(identificacionComprador), valor, fechaStr);
                    listaPujas.add(puja);
                } else {
                    // La línea no tiene el formato esperado, puedes manejarlo según tus necesidades
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
            }

            lector.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaPujas;
    }

    //Metodos adicionales para leer el archivo de backup---------------------------------------------------------------------------------------------------------------
    public static String obtenerCodigoAnuncioDesdeCadena(String cadena) {
        // Define un patrón de expresión regular para buscar el código del anuncio
        Pattern patron = Pattern.compile("codigo='(\\d+)'");

        // Crea un objeto Matcher para buscar el patrón en la cadena
        Matcher matcher = patron.matcher(cadena);

        // Si se encuentra una coincidencia, obtiene el código y lo retorna
        if (matcher.find()) {
            return matcher.group(1); // El código estará en el grupo 1
        } else {
            return null; // Código del anuncio no encontrado
        }
    }
}
