package ar.com.clovinn.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.clovinn.dto.OperacionDto;
import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.dto.TipoOperacion;
import ar.com.clovinn.services.ProductoService;
import ar.com.clovinn.validation.annotation.OperacionValida;

/**
 * Validador de operaciones. Se realizan las validaciones de operaciones para 
 * validar la compra y la venta de productos.
 * 
 * @author Paula Bourlot
 *
 */
public class ProductoOperacionValidator extends BaseValidator implements ConstraintValidator<OperacionValida, OperacionDto>{

	@Autowired
	private ProductoService productoService;
	
	@Override
	public void initialize(OperacionValida arg0) {
		//constructor vacio
	}

	@Override
	public boolean isValid(OperacionDto operacion, ConstraintValidatorContext context) {

		ProductoDto producto = productoService.buscarPorId(operacion.getIdProducto());
		
		//se valida que el producto que se este comprando o vendiendo exista
		if(producto == null) {
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate( "{producto.productoInexistente}" )
	        	.addPropertyNode( "idProducto" ).addConstraintViolation();
			
			return false;
		}
		else {
			//si la operacion es de venta, se valida si el stock es suficiente
			if(operacion.getTipoOperacion().equals(TipoOperacion.VENTA) && 
					producto.getStock() < operacion.getCantidad()) {
				context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate( "{producto.stockInsuficiente}" )
		        	.addPropertyNode( "cantidad" ).addConstraintViolation();
				
				return false;
			}
		}
		
		return true;
	}

}
