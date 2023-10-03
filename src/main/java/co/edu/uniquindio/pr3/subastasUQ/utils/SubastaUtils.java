package co.edu.uniquindio.pr3.subastasUQ.utils;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.*;
import co.edu.uniquindio.pr3.subastasUQ.persistencia.*;

import java.util.*;

public class SubastaUtils {
    public static Subasta inicializarDatos() {
        Subasta subasta = new Subasta("Subastas UQ", "Carrera 15 #12N, Armenia, Quindío");

        //Se crea una cuenta de Anunciante
        try {
            subasta.crearAnunciante("Juan José", "Arce Aristizabal", "1092851015", 18, subasta, "juanjarce", "nally41945090", "juanjtete2004@gmail.com", false);
        } catch (AnuncianteException | UsuarioEnUsoException e) {
            System.out.println(e.getMessage());
        }

        //Se crea una cuenta de comprador
        try {
            subasta.crearComprador("Daniel Felipe", "Correa de la Espriella", "1092455966", 18, subasta, "danielcorrea", "danielcorrea", "danielf.corread@uqvirtual.edu.co", false);
        } catch (UsuarioEnUsoException | CompradorException e) {
            System.out.println(e.getMessage());
        }

        //Obtenermos el anunciante para crea un producto
        Anunciante a1 = subasta.obtenerAnunciante("1092851015");

        //Creamos un Producto con Anunciante a1
        try {
            a1.crearProducto("P1", "Mazda 6", "Mazda 6 modelo 2023", "C:/ArchivosSubastasUQ/Images/Sedan1.png", 20000000.0, TipoProducto.VEHICULOS);
        } catch (ProductoException e) {
            System.out.println(e.getMessage());
        }

        //Creamos un Anuncio con a1
        try {
            a1.crearAnuncio("1", "02.10.2023", "03.10.2023", a1.getNombres(), "P1");
        } catch (AnuncioException | ProductoException e) {
            System.out.println(e.getMessage());
        }

        //Obtenemos el comprador
        Comprador c1 = subasta.obtenerComprador("1092455966");
        //Creamos una Puja con c1 y el anuncio con codigo "1"
        try {
            c1.realizarPuja("1", 22000000.0, "02.10.2023");
        } catch (PujaException e) {
            System.out.println(e.getMessage());
        }

        //Realizamos la venta de el anuncio "1" a la puja realizada
        try {
            a1.realizarVenta("1", "P1", 22000000.0, "02.10.2023", "1092455966", a1);
        } catch (AnuncianteException e) {
            System.out.println(e.getMessage());
        }

        //iniciarYSalvarDatosPrueba()
        //Se escriben los archivos con la informacion agregada
        BackupProducto.writeBackup(subasta.getListaProductos());
        BackupUsuario.writeBackup(subasta.getListaUsuarios());
        BackupAnuncio.writeBackup(subasta.getListaAnuncios());
        BackupPuja.writeBackup(subasta.getListaPujas());
        BackupCompra.writeBackup(subasta.getListaCompras());

        return subasta;
    }
}
