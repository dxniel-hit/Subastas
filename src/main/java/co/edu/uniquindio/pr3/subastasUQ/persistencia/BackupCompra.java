package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Compra;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BackupCompra {

    public static void writeBackup(List<Compra> listaCompras){
        File comprasRespaldo = new File("src/main/resources/persistencia/archivos/compras_Transaccion.txt");
        try {
            FileWriter writer = new FileWriter(comprasRespaldo); //Para escribir en el archivo

            for (Compra c : listaCompras) {
                writer.write(
                        c.getProducto()+"@"+
                                "@"+c.getValor() +"@"+
                                "@"+c.getFecha() +"@"+
                                "@"+c.getPago() +"@"+
                                "@"+c.getComprador().getIdentificacion() +"@"+
                                "@"+c.getAnunciante().getIdentificacion()+"\n"
                );
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static void appendToBackup(Compra c) {
        try {
            // Create a File object with the desired file name and location
            File comprasRespaldo = new File("src/main/resources/persistencia/archivos/compras_Transaccion.txt");

            // Create a FileWriter object to write to the file with the append parameter set to true
            FileWriter writer = new FileWriter(comprasRespaldo, true);

            //add the product information
            writer.write(c.getProducto()+"@"+
                    "@"+c.getValor() +"@"+
                    "@"+c.getFecha() +"@"+
                    "@"+c.getPago() +"@"+
                    "@"+c.getComprador().getIdentificacion() +"@"+
                    "@"+c.getAnunciante().getIdentificacion()+"\n");

            // Close the FileWriter object to save the changes
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

}
