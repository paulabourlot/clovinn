package ar.com.clovinn.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.clovinn.dto.CategoriaDto;
import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.error.ProductoException;
import ar.com.clovinn.mapper.ProductoMapper;
import ar.com.clovinn.model.Categoria;
import ar.com.clovinn.model.Producto;
import ar.com.clovinn.repository.CategoriaRepository;
import ar.com.clovinn.repository.ProductoRepository;
import ar.com.clovinn.services.ProductoService;

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProductoMapper productoMapper;
	
	/**
	 * Servicio para devolver un producto por su id
	 */
	@Override
	public ProductoDto buscarPorId(Integer idProducto) {
		Producto producto = productoRepository.findById(idProducto).orElse(null);
		return productoMapper.map(producto);
	}
	
	/**
	 * Servicio para listar todos los productos
	 * 
	 */
	@Override
	public List<ProductoDto> listarTodos() {
		List<Producto> productos = productoRepository.findAll();
		return productoMapper.map(productos);
	}

	/**
	 * Servicio para guardar un producto nuevo
	 * 
	 */
	@Override
	public ProductoDto guardarNuevo(ProductoDto productoDto) {
		Producto producto = productoMapper.map(productoDto);
		
		Producto productoActualizado = productoRepository.save(producto);
		return productoMapper.map(productoActualizado);
	}
	
	/**
	 * Servicio para actualizar un producto
	 * 
	 */
	@Override
	public ProductoDto actualizar(ProductoDto productoDto) {
		Producto producto = productoMapper.map(productoDto);
		
		Producto productoActualizado = productoRepository.save(producto);
		return productoMapper.map(productoActualizado);
	}

	/**
	 * Servicio para eliminar un producto
	 * 
	 */
	@Override
	public void eliminar(Integer idProducto) {
		productoRepository.findById(idProducto)
			.orElseThrow(() -> new ProductoException("No se puede determinar el producto"));
		
		productoRepository.deleteById(idProducto);
	}

	/**
	 * Servicio para buscar un producto por su código
	 * 
	 */
	@Override
	public ProductoDto buscarPorCodigo(String codigo) {
		Producto producto = productoRepository
				.findByCodigo(codigo)
				.orElse(null);
		return productoMapper.map(producto);
	}
	
	/**
	 * Servicio para listar todas las categorías
	 * 
	 */
	@Override
	public List<CategoriaDto> listarCategorias() {
		List<Categoria> lista = categoriaRepository.findAll();
		return productoMapper.mapListaCategorias(lista);
	}
}