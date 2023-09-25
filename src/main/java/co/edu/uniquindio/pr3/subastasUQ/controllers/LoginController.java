package co.edu.uniquindio.pr3.subastasUQ.controllers;

public class LoginController {

    public ModelFactoryController mfm;

    public LoginController(){
        System.out.println("Llamando al singleton desde EmpleadoServiceController");
        mfm = ModelFactoryController.getInstance();
    }

}
