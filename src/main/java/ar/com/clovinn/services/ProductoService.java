package ar.com.clovinn.services;

import java.util.List;

import ar.com.clovinn.dto.CategoriaDto;
import ar.com.clovinn.dto.ProductoDto;

public interface ProductoService {
	
	ProductoDto buscarPorId(Integer idProducto);
	
	List<ProductoDto> listarTodos();
	
	ProductoDto guardarNuevo(ProductoDto productoDto);
	
	ProductoDto actualizar(ProductoDto productoDto);

	void eliminar(Integer idProducto);

	ProductoDto buscarPorCodigo(String codigo);

	List<CategoriaDto> listarCategorias();
}
