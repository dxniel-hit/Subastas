package co.edu.uniquindio.pr3.subastasUQ.hilos;

import co.edu.uniquindio.pr3.subastasUQ.model.Subasta;
import co.edu.uniquindio.pr3.subastasUQ.utils.SubastaUtils;

public class IniciarYSalvarDatosPruebaThread extends Thread{
    @Override
    public void run() {
        Subasta s1 = SubastaUtils.inicializarDatos();
    }
}
