package co.edu.uniquindio.pr3.subastasUQ.hilos;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;

public class GuardarXMLThread extends Thread{
    @Override
    public void run() {
        ModelFactoryController.serializarXML();
    }
}
