package co.edu.uniquindio.pr3.subastasUQ.hilos;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;

public class CargarDatosArchivosThread extends Thread{
    @Override
    public void run() {
        ModelFactoryController.cargarDatosDesdeArchivos();
    }
}
