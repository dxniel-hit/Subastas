package co.edu.uniquindio.pr3.subastasUQ.hilos;

import co.edu.uniquindio.pr3.subastasUQ.persistencia.Persistencia;

public class CopiasRespaldoThread extends Thread{
    @Override
    public void run() {
        Persistencia.realizarCopiasRespaldo();
    }
}
