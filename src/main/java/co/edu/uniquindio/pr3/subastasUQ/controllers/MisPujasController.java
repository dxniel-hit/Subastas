package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.IMisPujasControllerService;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.MisPujasViewController;

public class MisPujasController implements IMisPujasControllerService {

    public ModelFactoryController mfm;

    public MisPujasController(){
        System.out.println("Llamando al singleton desde MisPujasController");
        mfm = ModelFactoryController.getInstance();
    }


    public void initMisPujasViewController(MisPujasViewController misPujasViewController) {
        mfm.initMisPujasViewController(misPujasViewController);
    }

    public void resetSeleccionAnuncio() {
        mfm.resetSeleccionAnuncio();
    }

    public boolean agregarPuja(PujaDTO pujaDto) {
        return mfm.agregarPuja(pujaDto);
    }

}
