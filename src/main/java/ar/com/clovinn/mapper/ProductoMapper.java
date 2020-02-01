package ar.com.clovinn.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import ar.com.clovinn.dto.CategoriaDto;
import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.model.Categoria;
import ar.com.clovinn.model.Producto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductoMapper {

    List<ProductoDto> map(List<Producto> lista);
    List<CategoriaDto> mapListaCategorias(List<Categoria> lista);
    
    @Mappings({
    	@Mapping(source = "categoria.id", target = "idCategoria"),
    	@Mapping(source = "categoria.nombre", target = "nombreCategoria")
    })
    ProductoDto map(Producto producto);
    
    @Mappings({
    	@Mapping(source = "idCategoria", target = "categoria.id")
    })
    Producto map(ProductoDto productoDto);
    
    Categoria map(CategoriaDto categoriaDto);
    
}
