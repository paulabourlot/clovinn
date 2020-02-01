package ar.com.clovinn.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import ar.com.clovinn.dto.OperacionDto;
import ar.com.clovinn.dto.TipoOperacion;

@Sql(scripts = {
	"/db/sql/insert-productos.sql"
})
public class OperacionControllerTest extends BaseControllerTest {

	private final String URL_OPERACION = "/operacion/";

	@Test
	@Rollback
	public void guardarNuevaCompra() throws Exception {
		
		OperacionDto operacion = OperacionDto.builder()
			.idProducto(100)
			.tipoOperacion(TipoOperacion.COMPRA)
			.cantidad(2)
			.build();
		
		MvcResult result = mockMvc.perform(post(URL_OPERACION)
				.param("operacion.idProducto", String.valueOf(operacion.getIdProducto()))
				.param("operacion.tipoOperacion", operacion.getTipoOperacion().name())
				.param("operacion.cantidad", String.valueOf(operacion.getCantidad()))
				)
			.andExpect(status().is3xxRedirection())
			.andReturn();
		
		assertThat(result.getResponse().getRedirectedUrl(), is("/producto"));
	}
	
	@Test
	@Rollback
	public void guardarNuevaVenta() throws Exception {
		
		OperacionDto operacion = OperacionDto.builder()
			.idProducto(102)
			.tipoOperacion(TipoOperacion.VENTA)
			.cantidad(1)
			.build();
		
		MvcResult result = mockMvc.perform(post(URL_OPERACION)
				.param("operacion.idProducto", String.valueOf(operacion.getIdProducto()))
				.param("operacion.tipoOperacion", operacion.getTipoOperacion().name())
				.param("operacion.cantidad", String.valueOf(operacion.getCantidad()))
				)
			.andExpect(status().is3xxRedirection())
			.andReturn();
		
		assertThat(result.getResponse().getRedirectedUrl(), is("/producto"));
	}

	@Test
	@Rollback
	public void guardarNuevaVentaStockInsuficienteFalla() throws Exception {
		
		Integer cantidadErrores = 1;
		
		OperacionDto operacion = OperacionDto.builder()
			.idProducto(100)
			.tipoOperacion(TipoOperacion.VENTA)
			.cantidad(1)
			.build();
		
		mockMvc.perform(post(URL_OPERACION)
				.param("operacion.idProducto", String.valueOf(operacion.getIdProducto()))
				.param("operacion.tipoOperacion", operacion.getTipoOperacion().name())
				.param("operacion.cantidad", String.valueOf(operacion.getCantidad()))
				)
			.andExpect(status().isOk())
			.andExpect(model().errorCount(cantidadErrores))
			.andReturn();
	}
	
	@Test
	@Rollback
	public void guardarNuevaVentaProductoInexistenteFalla() throws Exception {
		
		Integer cantidadErrores = 1;
		
		OperacionDto operacion = OperacionDto.builder()
			.idProducto(999)
			.tipoOperacion(TipoOperacion.VENTA)
			.cantidad(1)
			.build();
		
		mockMvc.perform(post(URL_OPERACION)
				.param("operacion.idProducto", String.valueOf(operacion.getIdProducto()))
				.param("operacion.tipoOperacion", operacion.getTipoOperacion().name())
				.param("operacion.cantidad", String.valueOf(operacion.getCantidad()))
				)
			.andExpect(status().isOk())
			.andExpect(model().errorCount(cantidadErrores))
			.andReturn();
	}
}