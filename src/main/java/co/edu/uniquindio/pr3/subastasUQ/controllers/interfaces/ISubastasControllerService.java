package co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.SubastasViewController;

public interface ISubastasControllerService {

    public void initSubastasViewController(SubastasViewController subastasViewController);
    public void initAnuncioSeleccionado(AnuncioDTO anuncioSeleccionado);

}
