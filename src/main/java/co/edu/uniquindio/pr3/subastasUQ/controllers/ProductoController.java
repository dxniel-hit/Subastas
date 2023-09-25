package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.IProductoControllerService;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.*;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.*;
import javafx.scene.image.*;

import java.util.*;

public class ProductoController implements IProductoControllerService {

    public ModelFactoryController mfm;

    public ProductoController(){
        System.out.println("Llamando al singleton desde EmpleadoServiceController");
        mfm = ModelFactoryController.getInstance();
    }

    @Override
    public boolean agregarProducto(ProductoDTO productoDto) {
        return mfm.agregarProducto(productoDto);
    }
}
