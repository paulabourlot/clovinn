package ar.com.clovinn.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import ar.com.clovinn.dto.OperacionDto;
import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.dto.TipoOperacion;
import ar.com.clovinn.error.OperacionException;

@Sql(scripts = {
		"/db/sql/insert-productos.sql",
})
public class OperacionServiceTest extends BaseServiceTest{
	
	@Autowired
	private OperacionService operacionService;
	@Autowired
	private ProductoService productoService;

	@Test
	@Rollback
	public void nuevaVenta() {
		OperacionDto operacion = OperacionDto.builder()
				.idProducto(102)
				.tipoOperacion(TipoOperacion.VENTA)
				.cantidad(2)
				.build();
		
		boolean resultado = operacionService.registrarOperacion(operacion);
		ProductoDto producto = productoService.buscarPorId(operacion.getIdProducto());
		
		assertThat(resultado, is(true));
		
		//el stock inicial es de 3 unidades
		assertThat(producto.getStock(), is(1));
	}
	
	@Test
	@Rollback
	public void nuevaCompra() {
		OperacionDto operacion = OperacionDto.builder()
				.idProducto(100)
				.tipoOperacion(TipoOperacion.COMPRA)
				.cantidad(1)
				.build();
		
		boolean resultado = operacionService.registrarOperacion(operacion);
		ProductoDto producto = productoService.buscarPorId(operacion.getIdProducto());
		
		assertThat(resultado, is(true));
		
		//el stock inicial es de 0 unidades
		assertThat(producto.getStock(), is(1));
	}
	
	@Test(expected = OperacionException.class)
	@Rollback
	public void nuevaOperacionSinTipo() {
		OperacionDto operacion = OperacionDto.builder()
				.idProducto(100)
//				.tipoOperacion(TipoOperacion.COMPRA)
				.cantidad(1)
				.build();
		
		operacionService.registrarOperacion(operacion);
	}
	
	@Test(expected = OperacionException.class)
	@Rollback
	public void nuevaVentaSinStockFalla() {
		OperacionDto operacion = OperacionDto.builder()
				.idProducto(100)
				.tipoOperacion(TipoOperacion.VENTA)
				.cantidad(2)
				.build();
		
		operacionService.registrarOperacion(operacion);
	}
}