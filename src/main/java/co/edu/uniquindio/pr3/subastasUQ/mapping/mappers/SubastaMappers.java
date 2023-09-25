package co.edu.uniquindio.pr3.subastasUQ.mapping.mappers;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

import java.util.*;

@Mapper
public interface SubastaMappers {

    SubastaMappers INSTANCE = Mappers.getMapper(SubastaMappers.class);

    @Named("productoToProductoDTO")
    ProductoDTO productoToProductoDTO(Producto producto);

    Producto productoDTOtoProducto(ProductoDTO productoDTO);

    @IterableMapping(qualifiedByName = "productoToProductoDTO")
    List<ProductoDTO> getProductosDTO(List<Producto> listaProductos);

}
