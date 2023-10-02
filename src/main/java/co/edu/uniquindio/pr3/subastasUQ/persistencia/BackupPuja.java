package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

}
