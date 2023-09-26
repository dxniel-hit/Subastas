package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.ILoginControllerService;

public class LoginController implements ILoginControllerService {

    public ModelFactoryController mfm;

    public LoginController(){
        System.out.println("Llamando al singleton desde LoginController");
        mfm = ModelFactoryController.getInstance();
    }

}
