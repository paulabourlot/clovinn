package ar.com.clovinn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.clovinn.dto.OperacionDto;
import ar.com.clovinn.form.ProductoForm;
import ar.com.clovinn.services.OperacionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/operacion")
public class OperacionController extends BaseController{

	protected static final String PRODUCTO = "producto/producto";

	@Autowired
	private OperacionService operacionService;
	
	/**
	 * Alta de nuevas operaciones (compra / venta) de productos.
	 * 
	 * @param productoForm formulario
	 * @param result vista de resultado
	 * @return
	 */
	@PostMapping
	public String operacion(@Validated ProductoForm productoForm, BindingResult result) {
		log.info(">> Alta de operación compra o venta");
		
		OperacionDto operacion = productoForm.getOperacion();
		if(!result.hasErrors()) {
			boolean resultado = operacionService.registrarOperacion(operacion);
			
			if(resultado) {
				agregarMensajeExitoPorCodigo(productoForm, CODIGO_MENSAJE_CAMBIOS_APLICADOS);
			}
			
			return "redirect:/producto";
		}
		return PRODUCTO;
	}
}