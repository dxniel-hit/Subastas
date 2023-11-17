package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

import javax.swing.*;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class Persistencia {

    //Metodos para cargar informacion quemada en SubastaUtils usando los archivos generados ----------------------------------------------------------------------------------------

    //cargarDatosDesdeArchivos()
    public static void cargarDatosDesdeArchivos(Subasta subasta){
        //Obtenemos los usuarios registrados en el archivo usuarios.txt
        List<Usuario> listaUsuarios = BackupUsuario.readBackup(subasta);
        //agregamos los usuarios a la clase subasta del parametro
        listaUsuarios.forEach(u -> {
            if(u.getTipoUsuario().equals(TipoUsuario.ANUNCIANTE)){
                try {
                    subasta.crearAnunciante(u.getNombres(), u.getApellidos(), u.getIdentificacion(), u.getEdad(), subasta, u.getUsuario(), u.getContrasenia(), u.getEmail(), false);
                } catch (AnuncianteException | UsuarioEnUsoException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(u.getTipoUsuario().equals(TipoUsuario.COMPRADOR)){
                try {
                    subasta.crearComprador(u.getNombres(), u.getApellidos(), u.getIdentificacion(), u.getEdad(), subasta, u.getUsuario(), u.getContrasenia(), u.getEmail(), false);
                } catch (UsuarioEnUsoException | CompradorException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        //Obtenemos el anunciante y el comprador agregados
        Anunciante a1 = subasta.obtenerAnunciante("1092851015");
        Comprador c1 = subasta.obtenerComprador("1092455966");

        //Obtenemos los productos registrados en el archivo productos.txt
        List<Producto> listaProductos = BackupProducto.readBackup();
        //agregamos los productos a el anunciante correspondiente
        listaProductos.forEach(p -> {
            try {
                a1.crearProducto(p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getImage(), p.getValorInicial(), p.getTipoProducto());
            } catch (ProductoException e) {
                System.out.println(e.getMessage());
            }
        });

        //Obtenemos los Anuncios registrados en anuncios.txt
        List<Anuncio> listaAnuncios = BackupAnuncio.readBackup(subasta);
        //agregamos los anuncios a el anunciante correspondiente
        listaAnuncios.forEach(a -> {
            try {
                a1.crearAnuncio(a.getCodigo(), a.getFechaInicio(), a.getFechaFinal(), a1.getNombres(), a.getProducto().getCodigo());
            } catch (AnuncioException | ProductoException e) {
                System.out.println(e.getMessage());
            }
        });

        //Obtenemos las Pujas registradas en pujas_Transaccion.txt
        List<Puja> listaPujas = BackupPuja.readBackup(subasta);
        //agregamos las pujas a el comprador correspondiente
        listaPujas.forEach( p -> {
            try {
                c1.realizarPuja(p.getAnuncio().getCodigo(), p.getValor(), p.getFecha());
            } catch (PujaException e) {
                System.out.println(e.getMessage());
            }
        });

        //Obtenemos las Compras en compras_Transaccion.txt
        List<Compra> listaCompras = BackupCompra.readBackup(subasta);
        //agregamos las compras con el anunciante correspondiente
        listaCompras.forEach( c -> {
            try {
                a1.realizarVenta("1", c.getProducto().getCodigo(), c.getValor(), c.getFecha(), c.getComprador().getIdentificacion(), a1);
            } catch (AnuncianteException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos para serializacion binaria ---------------------------------------------------------------------------------------------------------------------------------------------

    //guardarResourceBinario()
    public static void serializarBinario(String rutaArchivo, Subasta miSubasta) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            escribiendo_fichero.writeObject(miSubasta);
            escribiendo_fichero.close();
        }
        catch(Exception ignored) {
        }
    }

    //cargarResourceBinario()
    public static Subasta deserializarBinario(String rutaArchivo) {
        Subasta miSubasta = null;
        try {
            ObjectInputStream recuperando_ficheroConcesionario = new ObjectInputStream(new FileInputStream(rutaArchivo));
            miSubasta = (Subasta) recuperando_ficheroConcesionario.readObject();
            recuperando_ficheroConcesionario.close();
        }
        catch (Exception ignored) {

        }
        return miSubasta;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos para serializacion en archivos xml---------------------------------------------------------------------------------------------------------------------------------------

    //guardarResourceXML()
    public static void serializarXML(String rutaArchivo, Subasta miSubasta) {
        try{
            XMLEncoder codificadorXML;

            codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
            codificadorXML.writeObject(miSubasta);
            codificadorXML.close();
        }catch (Exception ignored){

        }
    }

    //cargarResourceXML()
    public static Subasta deserializarXML(String rutaArchivo) {
        XMLDecoder decodificadorXML;
        Subasta objetoXML=null;
        try{
            decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
            objetoXML = (Subasta) decodificadorXML.readObject();
            decodificadorXML.close();
        }catch (Exception ignored){

        }
        return objetoXML;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodo para copia archivo serializado (xml) en formato nombreArchivo_ddmmaaaa_hhmmss --------------------------------------------------------------------------------------------

    //Copar archivo de respaldo .xml
    public static void copiarArchivoConFechaHoraXML(String rutaOrigen, String directorioDestino) {
        try {
            File archivoOriginal = new File(rutaOrigen);
            if (archivoOriginal.exists() && archivoOriginal.isFile()) {
                // Obtener la fecha y hora actual
                SimpleDateFormat formatoFechaHora = new SimpleDateFormat("ddMMyyyy_HHmmss");
                String fechaHoraActual = formatoFechaHora.format(new Date());

                // Obtener el nombre del archivo original sin extensión
                String nombreArchivoSinExtension = archivoOriginal.getName().replaceFirst("[.][^.]+$", "");

                // Generar el nuevo nombre del archivo con la fecha y hora
                String nombreArchivoCopia = nombreArchivoSinExtension + "_" + fechaHoraActual + ".xml";

                // Crear el directorio destino si no existe
                File directorioDestinoFile = new File(directorioDestino);
                directorioDestinoFile.mkdirs();

                // Crear el archivo de destino con el nuevo nombre
                Path destino = new File(directorioDestino, nombreArchivoCopia).toPath();

                // Copiar el archivo original al destino
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(archivoOriginal.toPath(), destino, options);

                System.out.println("Copia exitosa: " + destino);
            } else {
                System.out.println("El archivo original no existe o no es un archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    //copiar archivo de respaldo .txt
    public static void copiarArchivoConFechaHoraTXT(String rutaOrigen, String directorioDestino) {
        try {
            File archivoOriginal = new File(rutaOrigen);
            if (archivoOriginal.exists() && archivoOriginal.isFile()) {
                // Obtener la fecha y hora actual
                SimpleDateFormat formatoFechaHora = new SimpleDateFormat("ddMMyyyy_HHmmss");
                String fechaHoraActual = formatoFechaHora.format(new Date());

                // Obtener el nombre del archivo original sin extensión
                String nombreArchivoSinExtension = archivoOriginal.getName().replaceFirst("[.][^.]+$", "");

                // Generar el nuevo nombre del archivo con la fecha y hora
                String nombreArchivoCopia = nombreArchivoSinExtension + "_" + fechaHoraActual + ".txt";

                // Crear el directorio destino si no existe
                File directorioDestinoFile = new File(directorioDestino);
                directorioDestinoFile.mkdirs();

                // Crear el archivo de destino con el nuevo nombre
                Path destino = new File(directorioDestino, nombreArchivoCopia).toPath();

                // Copiar el archivo original al destino
                Files.copy(archivoOriginal.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Copia exitosa: " + destino);
            } else {
                System.out.println("El archivo original no existe o no es un archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    //Rutas necesarias para realizar copias de archivos (cuando se inicia la aplicacion)
    public static final String RUTA_ARCHIVO_SUBASTAUQ = "src/main/resources/persistencia/SubastasUQ.xml";
    public static final String RUTA_ARCHIVO_COMPRAS = "src/main/resources/persistencia/archivos/compras_Transaccion.txt";

    public static void realizarCopiasRespaldo(){
        copiarArchivoConFechaHoraXML(RUTA_ARCHIVO_SUBASTAUQ, "src/main/resources/persistencia/respaldo");
        copiarArchivoConFechaHoraTXT(RUTA_ARCHIVO_COMPRAS, "src/main/resources/persistencia/respaldo");
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metdos para exportar archivos .csv ----------------------------------------------------------------------------------------------------------------------------------------------

    //Inicializamos las rutas de los archivos .txt (Anuncios y Compras)
    static String RUTA_ANUNCIOS = "src/main/resources/persistencia/archivos/anuncios.txt";
    static String RUTA_COMPRAS = "src/main/resources/persistencia/archivos/compras_Transaccion.txt";

    //Metodo para exportar la informacion de anuncios.txt a un archivo .csv
    public static void convertAnunciosTxtToCsv(String outputFolderPath) {
        if (outputFolderPath == null) {
            System.out.println("La operación fue cancelada por el usuario.");
            return;
        }

        String outputFileName = outputFolderPath + File.separator + "anuncios.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ANUNCIOS));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Reemplaza "@@" por ","
                line = line.replace("@@", ",");
                // Escribe la línea en el archivo .csv
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Conversión completada. Archivo .csv creado en: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para exportar la informacion de compras_Transaccion.txt a un archivo .csv
    public static void convertComprasTxtToCsv(String outputFolderPath) {
        if (outputFolderPath == null) {
            System.out.println("La operación fue cancelada por el usuario.");
            return;
        }

        String outputFileName = outputFolderPath + File.separator + "compras.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_COMPRAS));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Reemplaza "@@" por ","
                line = line.replace("@@", ",");
                // Escribe la línea en el archivo .csv
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Conversión completada. Archivo .csv creado en: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Manejo de archivos en disco del PC ---------------------------------------------------------------------------------------------------------
    public static void copyFile(String sourcePath, String destinationPath) {
        try {
            File sourceFile = new File(sourcePath);
            File destinationFile = new File(destinationPath);

            // Comprueba si el archivo de origen existe
            if (sourceFile.exists() && sourceFile.isFile()) {
                // Copia el archivo al destino especificado
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo copiado exitosamente a " + destinationPath);
            } else {
                System.err.println("El archivo de origen no existe o no es un archivo válido.");
            }
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    //Metodos para lectura y escritura de archivos .xml ---------------------------------------------------------------------------------------------

    public static String leerArchivoXML(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }

    public static void escribirArchivoXML(String contenido, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
}
