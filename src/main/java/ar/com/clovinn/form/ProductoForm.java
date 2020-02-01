package ar.com.clovinn.form;

import java.util.List;

import javax.validation.Valid;

import ar.com.clovinn.dto.CategoriaDto;
import ar.com.clovinn.dto.OperacionDto;
import ar.com.clovinn.dto.ProductoDto;
import ar.com.clovinn.validation.annotation.ProductoValido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ProductoValido
public class ProductoForm extends BaseForm {
	
	@Valid
	private ProductoDto producto;
	
	@Valid
	private OperacionDto operacion;
	
	private List<ProductoDto> listaProductos;
	private List<CategoriaDto> listaCategorias;
}
