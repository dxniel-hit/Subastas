package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.ISubastasControllerService;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.persistencia.Persistencia;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.SubastasViewController;

public class SubastasController implements ISubastasControllerService {

    public ModelFactoryController mfm;

    public SubastasController(){
        System.out.println("Llamando al singleton desde SubastasController");
        mfm = ModelFactoryController.getInstance();
    }

    public void initSubastasViewController(SubastasViewController subastasViewController) {
        mfm.initSubastasViewController(subastasViewController);
    }

    public void initAnuncioSeleccionado(AnuncioDTO anuncioSeleccionado) {
        mfm.initAnuncioSeleccionado(anuncioSeleccionado);
    }

    //Metodo para exportar los anuncios en formato .csv
    public void convertAnunciosTxtToCsv(String outputFolderPath){
        mfm.convertAnunciosTxtToCsv(outputFolderPath);
    }

    //Metodo para exportar las compras en formato .csv
    public void convertComprasTxtToCsv(String outputFolderPath){
        mfm.convertComprasTxtToCsv(outputFolderPath);
    }

}
