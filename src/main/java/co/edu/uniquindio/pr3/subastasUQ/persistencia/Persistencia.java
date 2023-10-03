package co.edu.uniquindio.pr3.subastasUQ.persistencia;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

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
}
