package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastasUQ.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackupAnuncio {
    public static void writeBackup(List<Anuncio> listaAnuncios){
        File anunciosRespaldo = new File("src/main/resources/persistencia/archivos/anuncios.txt");
        try {
            FileWriter writer = new FileWriter(anunciosRespaldo); //Para escribir en el archivo

            for (Anuncio a : listaAnuncios) {
                //Se intenta coger la compra "is!=null"
                Compra c = null;
                try{
                    c = a.getCompra();
                }catch (Exception ignored){

                }

                writer.write(
                        a.getCodigo()+"@"+
                                "@"+a.getFechaInicio()+"@"+
                                "@"+a.getFechaFinal()+"@"+
                                "@"+a.getNombreAnunciante()+"@"+
                                "@"+a.getProducto()+"@"+
                                "@"+c+"@"+
                                "@"+a.getListaPujas()+"\n"
                );
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static void appendToBackup(Anuncio a) {
        try {
            // Create a File object with the desired file name and location
            File anunciosRespaldo = new File("src/main/resources/persistencia/archivos/anuncios.txt");

            // Create a FileWriter object to write to the file with the append parameter set to true
            FileWriter writer = new FileWriter(anunciosRespaldo, true);

            //Se intenta coger la compra "is!=null"
            Compra c = null;
            try{
                c = a.getCompra();
            }catch (Exception ignored){

            }

            //add the advertisement information
            writer.write(
                    a.getCodigo()+"@"+
                            "@"+a.getFechaInicio()+"@"+
                            "@"+a.getFechaFinal()+"@"+
                            "@"+a.getNombreAnunciante()+"@"+
                            "@"+a.getProducto()+"@"+
                            "@"+c+"@"+
                            "@"+a.getListaPujas()+"\n"
            );

            // Close the FileWriter object to save the changes
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static List<Anuncio> readBackup(Subasta subasta) {
        List<Anuncio> listaAnuncios = new ArrayList<Anuncio>();
        File anunciosRespaldo = new File("src/main/resources/persistencia/archivos/anuncios.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(anunciosRespaldo));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@@");
                if (parts.length == 7) { // Asumiendo que tienes 7 partes por línea (ajusta según tu estructura)
                    String codigo = parts[0];
                    String fechaInicio = parts[1];
                    String fechaFinal = parts[2];
                    String nombreAnunciante = parts[3];
                    String producto = parts[4];

                    // Ahora, debes crear objetos Anuncio con esta información
                    Anuncio anuncio = new Anuncio(codigo, fechaInicio, fechaFinal, nombreAnunciante, subasta.obtenerProducto(obtenerCodigoProductoDesdeLinea(producto)), null);
                    // Configura los otros atributos del anuncio según tu estructura

                    listaAnuncios.add(anuncio);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return listaAnuncios;
    }

    //Metodos adicionales para la lectura del archivo----------------------------------------------------------------------------------------------
    public static String obtenerCodigoProductoDesdeLinea(String linea) {
        // Define un patrón de expresión regular para buscar el código
        Pattern patron = Pattern.compile("codigo='(.*?)'");
        Matcher matcher = patron.matcher(linea);

        // Si se encuentra una coincidencia, se obtiene el código
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null; // Retorna null si el código no se encuentra en la línea
        }
    }
}
