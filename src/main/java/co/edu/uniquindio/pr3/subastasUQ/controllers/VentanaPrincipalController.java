package co.edu.uniquindio.pr3.subastasUQ.controllers;

public class VentanaPrincipalController {

    public ModelFactoryController mfm;

    public VentanaPrincipalController(){
        System.out.println("Llamando al singleton desde VentanaPrincipalController");
        mfm = ModelFactoryController.getInstance();
    }
}
