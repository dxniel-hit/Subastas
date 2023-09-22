package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.services.IProductoControllerService;

public class ProductoController implements IProductoControllerService {

    public ModelFactoryController mfm;

    public ProductoController(){
        System.out.println("Llamando al singleton desde ProductoController");
        mfm = ModelFactoryController.getInstance();
    }

}