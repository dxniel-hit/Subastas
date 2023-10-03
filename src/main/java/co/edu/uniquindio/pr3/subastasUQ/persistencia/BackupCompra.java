package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.model.Compra;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Subasta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static List<Compra> readBackup(Subasta subasta) {
        List<Compra> listaCompras = new ArrayList<Compra>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/persistencia/archivos/compras_Transaccion.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en partes utilizando "@" como separador
                String[] partes = linea.split("@@");

                // Verificar si hay suficientes elementos en la línea para crear una Compra
                if (partes.length >= 6) {
                    // Obtener los valores de cada campo desde las partes divididas
                    String producto = partes[0];
                    double valor = Double.parseDouble(partes[1]);
                    String fecha = partes[2];
                    boolean pago = Boolean.parseBoolean(partes[3]);
                    String identificacionComprador = partes[4];
                    String identificacionAnunciante = partes[5];

                    // Crear una instancia de Compra y agregarla a la lista
                    Compra compra = new Compra(subasta.obtenerProducto(obtenerCodigoProductoDesdeLinea(producto)), valor, fecha, subasta.obtenerComprador(identificacionComprador), subasta.obtenerAnunciante(identificacionAnunciante));
                    listaCompras.add(compra);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaCompras;
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
