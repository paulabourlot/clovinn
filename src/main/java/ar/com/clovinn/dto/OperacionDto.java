package ar.com.clovinn.dto;

import javax.validation.constraints.NotNull;

import ar.com.clovinn.validation.annotation.OperacionValida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of="idProducto", callSuper=false)
@OperacionValida
public class OperacionDto {

	@NotNull
	private Integer idProducto;

	@NotNull
	private TipoOperacion tipoOperacion;
	
	@NotNull
	private Integer cantidad;
	
}
