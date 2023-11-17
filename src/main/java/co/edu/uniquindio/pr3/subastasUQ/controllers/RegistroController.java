package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

import static co.edu.uniquindio.pr3.subastasUQ.rabbitmq.utils.Constantes.QUEUE_PRODUCTOR;

public class RegistroController implements IRegistroControllerService {
    public ModelFactoryController mfm;

    public RegistroController(){
        System.out.println("Llamando al singleton desde RegistroController");
        mfm = ModelFactoryController.getInstance();
    }

    @Override
    public void producirMensaje(String message) {
        mfm.producirMensaje(message);
    }

}
