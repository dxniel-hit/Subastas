package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;

import java.io.*;
import java.util.ArrayList;
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
                                "@"+p.isAnunciado()+"\n"
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
                    "@"+p.isAnunciado()+"\n");

            // Close the FileWriter object to save the changes
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getCause());
        }
    }

    public static List<Producto> readBackup() {
        List<Producto> listaProductos = new ArrayList<Producto>();

        try {
            File archivo = new File("src/main/resources/persistencia/archivos/productos.txt");
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("@@");
                if (partes.length == 7) { // Verificar que la línea tenga el formato esperado
                    String codigo = partes[0];
                    String nombre = partes[1];
                    String descripcion = partes[2];
                    String imagen = partes[3];
                    double valorInicial = Double.parseDouble(partes[4]);
                    TipoProducto tipoProducto = TipoProducto.valueOf(partes[5]);
                    String anunciado = partes[6];

                    // Crear un producto y agregarlo a la lista
                    Producto producto = new Producto(codigo, nombre, descripcion, imagen, valorInicial, tipoProducto);
                    listaProductos.add(producto);
                } else {
                    // La línea no tiene el formato esperado, puedes manejarlo según tus necesidades
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
            }

            lector.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaProductos;
    }
}
