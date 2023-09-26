package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.*;

public class RegistroController implements IRegistroControllerService {
    public ModelFactoryController mfm;

    public RegistroController(){
        System.out.println("Llamando al singleton desde RegistroController");
        mfm = ModelFactoryController.getInstance();
    }

}
