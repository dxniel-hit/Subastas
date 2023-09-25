package co.edu.uniquindio.pr3.subastasUQ.utils;

import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.*;

import java.util.*;

public class SubastaUtils {

    public static Subasta inicializarDatos() {
        Subasta subasta = new Subasta();
        List<Producto> listaProductos = new ArrayList<Producto>();
        subasta.setListaProductos(listaProductos);


        Producto producto1 = new Producto();
        producto1.setCodigo("P1");
        producto1.setNombre("Celular");
        producto1.setTipoProducto(TipoProducto.TECNOLOGIA);
        producto1.setDescripcion("Dispositivo que te facilita la vida");
        producto1.setValorInicial(780000.0);
        System.out.println("TEST");
        subasta.getListaProductos().add(producto1);

        System.out.println("Información de la Subastera creada");

        return subasta;
    }
}
