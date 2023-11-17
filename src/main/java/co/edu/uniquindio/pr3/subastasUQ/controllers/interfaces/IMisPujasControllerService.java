package co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.MisPujasViewController;

public interface IMisPujasControllerService {

    public void initMisPujasViewController(MisPujasViewController misPujasViewController);
    public void resetSeleccionAnuncio();
    public boolean agregarPuja(PujaDTO pujaDto);
    public void producirMensaje(String message);

}
