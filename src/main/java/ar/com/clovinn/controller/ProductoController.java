package ar.com.clovinn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.clovinn.dto.CategoriaDto;
import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.form.ProductoForm;
import ar.com.clovinn.services.ProductoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/producto")
public class ProductoController extends BaseController{

	private static final String PRODUCTO = "producto/producto";
	private static final String PRODUCTO_LISTA = "producto/producto-lista";
	
	@Autowired
	private ProductoService productoService;
	
	/**
	 * Vista de lista de productos
	 * 
	 * @param productoForm
	 * @return
	 */
	@GetMapping
	public String formularioListado(ProductoForm productoForm) {
		List<ProductoDto> lista = productoService.listarTodos();
		productoForm.setListaProductos(lista);
		
		return PRODUCTO_LISTA;
	}
	
	/**
	 * Vista de alta de nuevo producto
	 * 
	 * @param productoForm
	 * @return
	 */
	@GetMapping("/alta")
	public String formularioAlta(ProductoForm productoForm) {
		completarDatosMaestros(productoForm);
		return PRODUCTO;
	}
	
	/**
	 * Vista de edición de producto
	 * 
	 * @param idProducto
	 * @param productoForm
	 * @return
	 */
	@GetMapping("/edit")
	public String editar(@RequestParam Integer idProducto, ProductoForm productoForm) {
		
		ProductoDto productoDto = productoService.buscarPorId(idProducto);
		productoForm.setProducto(productoDto);
		
		completarDatosMaestros(productoForm);
		
		return PRODUCTO;
	}
	
	/**
	 * Alta de nuevos productos
	 * 
	 * @param productoForm
	 * @param result
	 * @return
	 */
	@PostMapping
	public String guardar(@Validated ProductoForm productoForm, BindingResult result) {
		log.info(">> Alta de producto");
		
		ProductoDto producto = productoForm.getProducto();
		if(!result.hasErrors()) {
			ProductoDto productoActualizada = productoService.guardarNuevo(producto);
			
			agregarMensajeExitoPorCodigo(productoForm, CODIGO_MENSAJE_CAMBIOS_APLICADOS);
			productoForm.setProducto(productoActualizada);
		}
		
		completarDatosMaestros(productoForm);
		return PRODUCTO;
	}
	
	/**
	 * Actualización de productos existentes
	 * 
	 * @param productoForm
	 * @param result
	 * @return
	 */
	@PutMapping
	public String actualizar(@Validated ProductoForm productoForm, BindingResult result) {
		log.info(">> Actualización de producto");
		
		ProductoDto producto = productoForm.getProducto();
		if(!result.hasErrors()) {
			ProductoDto productoActualizada = productoService.actualizar(producto);
			
			agregarMensajeExitoPorCodigo(productoForm, CODIGO_MENSAJE_CAMBIOS_APLICADOS);
			productoForm.setProducto(productoActualizada);
		}
		
		completarDatosMaestros(productoForm);
		return PRODUCTO;
	}
	
	/**
	 * Eliminación de productos
	 * 
	 * @param idProducto
	 * @param productoForm
	 * @param redirectAttributes
	 * @return
	 */
	@DeleteMapping("{idProducto}")
	public String eliminar(@PathVariable Integer idProducto, ProductoForm productoForm,
			RedirectAttributes redirectAttributes) {
		log.info(">> Eliminación de producto");
		
		try {
			productoService.eliminar(idProducto);
			agregarMensajeExitoPorCodigo(productoForm, CODIGO_MENSAJE_CAMBIOS_APLICADOS);
		}
		catch (DataIntegrityViolationException e) {
			agregarMensajeErrorPorCodigo(productoForm, CODIGO_MENSAJE_ERROR_INTEGRIDAD);
		}
		catch (Exception e) {
			agregarMensajeError(productoForm, e.getMessage());
		}
		
		redirectAttributes.addFlashAttribute(productoForm);
		return "redirect:/producto";
	}

	/**
	 * Se completan los datos usados en las vistas de productos
	 * 
	 * @param productoForm
	 */
	private void completarDatosMaestros(ProductoForm productoForm) {
		List<CategoriaDto> lista = productoService.listarCategorias();
		productoForm.setListaCategorias(lista);
	}
}