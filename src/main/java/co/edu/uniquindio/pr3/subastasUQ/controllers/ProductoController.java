package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.*;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.*;
import javafx.scene.image.*;

import java.util.*;

public class ProductoController implements IProductoControllerService {

    ModelFactoryController modelFactoryController;

    public ProductoController() {
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<ProductoDTO> obtenerProductos() {
        return modelFactoryController.obtenerProductos();
    }

    @Override
    public Producto crearProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        return null;
    }

    @Override
    public void imprimirProducto(String codigo) throws ProductoException {

    }

    @Override
    public boolean actualizarProducto(String codigo, String nombre, String descripcion, Image image, Double valorInicial, TipoProducto tipoProducto) throws ProductoException {
        return false;
    }

    @Override
    public boolean eliminarProducto(String codigo) throws ProductoException {
        return false;
    }
}
