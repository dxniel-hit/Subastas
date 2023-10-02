package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Compra;
import co.edu.uniquindio.pr3.subastasUQ.model.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

}
