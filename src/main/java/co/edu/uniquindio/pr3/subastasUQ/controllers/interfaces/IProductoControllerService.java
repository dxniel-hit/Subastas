package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import javafx.scene.image.Image;

import java.util.*;

public interface IProductoControllerService {

    List<ProductoDTO> obtenerProductos();

    public Producto crearProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException;

    public void imprimirProducto(String codigo) throws ProductoException;

    public boolean actualizarProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException;

    public boolean eliminarProducto(String codigo) throws ProductoException;
}
