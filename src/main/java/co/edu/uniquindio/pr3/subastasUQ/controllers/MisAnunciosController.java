package co.edu.uniquindio.pr3.subastasUQ.controllers;

import co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces.IMisAnunciosControllerService;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.ProductoDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.MisAnunciosViewController;
import javafx.collections.ObservableList;

import java.util.List;

public class MisAnunciosController implements IMisAnunciosControllerService {

    public ModelFactoryController mfm;

    public MisAnunciosController(){
        System.out.println("Llamando al singleton desde MisAnunciosController");
        mfm = ModelFactoryController.getInstance();
    }

    public void initMisAnunciosViewController(MisAnunciosViewController misAnunciosViewController) {
        mfm.initMisAnunciosViewController(misAnunciosViewController);
    }

    public ProductoDTO obtenerProductoDto(String codigoProducto) {
        return mfm.obtenerProductoDto(codigoProducto);
    }

    public Anuncio obtenerAnuncio(String codigoAnuncio) {
        return mfm.obtenerAnuncio(codigoAnuncio);
    }

    public List<PujaDTO> obtenerPujasDto(List<Puja> listaPujas) {
        return mfm.obtenerPujasDto(listaPujas);
    }

    public boolean agregarAnuncio(AnuncioDTO anuncioDto) {
        return mfm.agregarAnuncio(anuncioDto);
    }

    public Producto poductoDTOtoPRoducto(ProductoDTO productoSeleccionado) {
        return mfm.mapper.productoDTOtoProducto(productoSeleccionado);
    }

}
