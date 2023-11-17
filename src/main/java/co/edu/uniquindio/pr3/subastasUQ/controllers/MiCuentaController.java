package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.IMiCuentaControllerService;

public class MiCuentaController implements IMiCuentaControllerService {

    public ModelFactoryController mfm;

    public MiCuentaController(){
        System.out.println("Llamando al singleton desde MiCuentaController");
        mfm = ModelFactoryController.getInstance();
    }

    @Override
    public void producirMensaje(String message) {
        mfm.producirMensaje(message);
    }

}
