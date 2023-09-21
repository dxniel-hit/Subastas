package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.services.IRegistroControllerService;

public class RegistroController implements IRegistroControllerService {
    public ModelFactoryController mfm;

    public RegistroController(){
        System.out.println("Llamando al singleton desde EmpleadoServiceController");
        mfm = ModelFactoryController.getInstance();
    }
}
