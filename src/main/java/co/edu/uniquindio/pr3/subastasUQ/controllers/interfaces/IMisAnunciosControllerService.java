package co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.ProductoDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.MisAnunciosViewController;
import javafx.collections.ObservableList;

import java.util.List;

public interface IMisAnunciosControllerService {

    public void initMisAnunciosViewController(MisAnunciosViewController misAnunciosViewController);
    public ProductoDTO obtenerProductoDto(String codigoProducto);
    public Anuncio obtenerAnuncio(String codigoAnuncio);
    public List<PujaDTO> obtenerPujasDto(List<Puja> listaPujas);
    public boolean agregarAnuncio(AnuncioDTO anuncioDto);

    public boolean actualizarAnuncio(String codigoAnuncio, AnuncioDTO anuncioDTO);

    public boolean eliminarAnuncio(String codigoAnuncio);
    public Producto poductoDTOtoPRoducto(ProductoDTO productoSeleccionado);

}
