package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BackupProducto {

    public static void writeBackup(List<Producto> listaProductos){
        File productosRespaldo = new File("src/main/resources/persistencia/archivos/productos.txt");
        try {
            FileWriter writer = new FileWriter(productosRespaldo); //Para escribir en el archivo

            for (Producto p : listaProductos) {
                writer.write(
                        p.getCodigo()+"@"+
                                "@"+p.getNombre() +"@"+
                                "@"+p.getDescripcion() +"@"+
                                "@"+p.getImage() +"@"+
                                "@"+p.getValorInicial() +"@"+
                                "@"+p.getTipoProducto() +"@"+
                                "@"+p.isAnunciado() +"\n"
                );
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static void appendToBackup(Producto p) {
        try {
            // Create a File object with the desired file name and location
            File productosRespaldo = new File("src/main/resources/persistencia/archivos/productos.txt");

            // Create a FileWriter object to write to the file with the append parameter set to true
            FileWriter writer = new FileWriter(productosRespaldo, true);

            //add the product information
            writer.write(p.getCodigo()+"@"+
                    "@"+p.getNombre() +"@"+
                    "@"+p.getDescripcion() +"@"+
                    "@"+p.getImage() +"@"+
                    "@"+p.getValorInicial() +"@"+
                    "@"+p.getTipoProducto() +"@"+
                    "@"+p.isAnunciado() +"\n");

            // Close the FileWriter object to save the changes
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

}
