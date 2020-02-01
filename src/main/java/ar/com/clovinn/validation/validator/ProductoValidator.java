package ar.com.clovinn.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.form.ProductoForm;
import ar.com.clovinn.services.ProductoService;
import ar.com.clovinn.validation.annotation.ProductoValido;

/**
 * Validador de producto. Se realizan todas las validaciones de las reglas de negocio 
 * establecidas
 * 
 * @author Paula Bourlot
 *
 */
public class ProductoValidator extends BaseValidator implements ConstraintValidator<ProductoValido, ProductoForm>{

	@Autowired
	private ProductoService productoService;
	
	@Override
	public void initialize(ProductoValido arg0) {
		//constructor vacio
	}

	@Override
	public boolean isValid(ProductoForm productoForm, ConstraintValidatorContext context) {
		ProductoDto producto = productoForm.getProducto();
		
		//la operacion no es sobre el objeto producto
		if(producto == null) {
			return true;
		}
		
		/*
		 * Si se esta dando de alta el producto, se valida que no se ingrese uno con 
		 * un código ya existente
		 */
		if(producto.getId() == null) {
			ProductoDto productoCodigo = productoService.buscarPorCodigo(producto.getCodigo());
			
			if(productoCodigo != null) {
				context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate( "{producto.codigoExistente}" )
		        	.addPropertyNode( "producto.codigo" ).addConstraintViolation();
				
				return false;
			}
		}

		return true;
	}

}
