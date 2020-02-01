package ar.com.clovinn.controller;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import ar.com.clovinn.dto.ProductoDto;

@Sql(scripts = {
	"/db/sql/insert-productos.sql"
})
public class ProductoControllerTest extends BaseControllerTest {

	private final String URL_PRODUCTO = "/producto/";

	@Test
	@Rollback
	public void listarTodos() throws Exception {

		mockMvc.perform(get(URL_PRODUCTO))
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE_HTML))
			.andExpect(model().attribute("productoForm", 
					hasProperty("listaProductos", hasSize(3))));
	}
	
	@Test
	@Rollback
	public void formulario() throws Exception {

		String url = URL_PRODUCTO + "alta";
		
		mockMvc.perform(get(url)
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE_HTML))
			.andExpect(model().attribute("productoForm", 
					hasProperty("producto", hasProperty("id", is(nullValue())))));
	}
	
	@Test
	@Rollback
	public void guardarNuevoProducto() throws Exception {
		
		ProductoDto producto = ProductoDto.builder()
			.nombre("PRODUCTO 1")
			.idCategoria(4)
			.codigo("PP0012")
			.stock(0)
			.build();
		
		mockMvc.perform(post(URL_PRODUCTO)
				
				.param("producto.nombre", producto.getNombre())
				.param("producto.codigo", producto.getCodigo())
				.param("producto.idCategoria", String.valueOf(producto.getIdCategoria()))
				.param("producto.stock", String.valueOf(producto.getStock()))
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE_HTML))
			.andExpect(model().attribute("productoForm", 
					hasProperty("producto", hasProperty("id", is(notNullValue())))));
	}
	
	@Test
	@Rollback
	public void guardarNuevoProductoIncompletosFalla() throws Exception {
		
		Integer cantidadErroresEsperados = 1;
		ProductoDto producto = ProductoDto.builder()
//			.nombre("PRODUCTO 1")
			.idCategoria(4)
			.codigo("PP0012")
			.stock(0)
			.build();
		
		mockMvc.perform(post(URL_PRODUCTO)
				
				.param("producto.nombre", producto.getNombre())
				.param("producto.codigo", producto.getCodigo())
				.param("producto.idCategoria", String.valueOf(producto.getIdCategoria()))
				.param("producto.stock", String.valueOf(producto.getStock()))
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE_HTML))
			.andExpect(model().errorCount(cantidadErroresEsperados));
	}
	
	@Test
	@Rollback
	public void editar() throws Exception {

		ProductoDto producto = ProductoDto.builder()
				.id(102)
				.nombre("VASO MARVEL")
				.codigo("VA002")
				.idCategoria(2)
				.nombreCategoria("VASOS")
				.stock(3)
				.build();
		String url = URL_PRODUCTO + "edit?idProducto=" + producto.getId();
		
		mockMvc.perform(get(url)
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE_HTML))
			.andExpect(model().attribute("productoForm", 
					hasProperty("producto", sameBeanAs(producto))));
	}
	
	@Test
	@Rollback
	public void actualizarProducto() throws Exception {
		
		ProductoDto producto = ProductoDto.builder()
			.id(100)
			.nombre("PRODUCTO 1")
			.idCategoria(2)
			.codigo("PP0012")
			.stock(0)
			.build();
		
		mockMvc.perform(put(URL_PRODUCTO)
				.param("producto.id", String.valueOf(producto.getId()))
				.param("producto.nombre", producto.getNombre())
				.param("producto.codigo", producto.getCodigo())
				.param("producto.idCategoria", String.valueOf(producto.getIdCategoria()))
				.param("producto.stock", String.valueOf(producto.getStock()))
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE_HTML))
			.andExpect(model().attribute("productoForm", 
					hasProperty("producto", hasProperty("codigo", is(producto.getCodigo())))));
	}
	
	@Test
	@Rollback
	public void eliminar() throws Exception {

		Integer idProducto = 100;
		String url = URL_PRODUCTO + idProducto;
		
		mockMvc.perform(delete(url)
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(flash().attribute("productoForm", 
					hasProperty("mensajeExito", is(notNullValue()))));
	}
	
	@Test
	@Rollback
	public void eliminarProductoInexistenteFalla() throws Exception {

		Integer idProducto = 10;
		String url = URL_PRODUCTO + idProducto;
		
		mockMvc.perform(delete(url)
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(flash().attribute("productoForm", 
					hasProperty("mensajeError", is(notNullValue()))));
	}
	
}