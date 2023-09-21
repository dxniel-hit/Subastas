package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.services.ISubastasQuindioControllerService;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.ISubastasQuindio;

public class SubastasQuindioController implements ISubastasQuindioControllerService {

    public ModelFactoryController mfm;

    public SubastasQuindioController(){
        System.out.println("Llamando al singleton desde EmpleadoServiceController");
        mfm = ModelFactoryController.getInstance();
    }

}
