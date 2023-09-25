package co.edu.uniquindio.pr3.subastasUQ.controllers;

public class MiCuentaController {

    public ModelFactoryController mfm;

    public MiCuentaController(){
        System.out.println("Llamando al singleton desde EmpleadoServiceController");
        mfm = ModelFactoryController.getInstance();
    }

}
