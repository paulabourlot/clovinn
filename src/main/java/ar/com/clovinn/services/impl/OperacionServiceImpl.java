package ar.com.clovinn.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.clovinn.dto.OperacionDto;
import ar.com.clovinn.dto.TipoOperacion;
import ar.com.clovinn.error.OperacionException;
import ar.com.clovinn.error.ProductoException;
import ar.com.clovinn.model.Producto;
import ar.com.clovinn.repository.ProductoRepository;
import ar.com.clovinn.services.OperacionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class OperacionServiceImpl implements OperacionService {

	@Autowired
	private ProductoRepository productoRepository;
	
	/**
	 * Servicio para registrar operaciones de compra y venta de productos
	 * 
	 */
	@Override
	public boolean registrarOperacion(OperacionDto operacion) {
		TipoOperacion tipo = operacion.getTipoOperacion();
		if(tipo != null) {
			return tipo.equals(TipoOperacion.COMPRA) ?
					comprar(operacion) : vender(operacion);
		}
		else {
			log.error("Intento fallido de registro de operación sin tipo");
			throw new OperacionException("El tipo de operación es requerido");
		}
	}
	
	/**
	 * Servicio para registrar la venta de productos (baja de stock).
	 * Se valida que el producto exista y que haya stock suficiente para la venta.
	 * 
	 * @param operacion
	 * @return
	 */
	private boolean vender(OperacionDto operacion) {
		Producto producto = productoRepository.findById(operacion.getIdProducto())
				.orElseThrow(() -> new ProductoException("No se puede determinar el producto"));
		
		Integer stock = producto.getStock() - operacion.getCantidad();
		if(stock < 0) {
			throw new OperacionException("Stock insuficiente para completar la operación");
		}
		
		producto.setStock(stock);
		productoRepository.save(producto);
		
		return true;
	}
	
	/**
	 * Servicio para registrar la compra de productos (alta de stock)
	 * Se valida que el producto exista.
	 * 
	 * @param operacion
	 * @return
	 */
	private boolean comprar(OperacionDto operacion) {
		Producto producto = productoRepository.findById(operacion.getIdProducto())
				.orElseThrow(() -> new ProductoException("No se puede determinar el producto"));
		
		Integer stock = producto.getStock() + operacion.getCantidad();
		producto.setStock(stock);
		productoRepository.save(producto);
		
		return true;
	}

	
}