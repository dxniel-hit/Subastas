package co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import javafx.scene.image.Image;

import java.util.*;

public interface IProductoControllerService {

    public boolean agregarProducto(ProductoDTO productoDto);
    public boolean renovarProducto(String codigoProducto, ProductoDTO productoDTO);
    public boolean expelerProducto(String codigo);
    public List<ProductoDTO> obtenerProductosAnunciante();

}
