package ar.com.clovinn.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of="id", callSuper=false)
public class ProductoDto implements BaseDto {

	private Integer id;
	
	@NotEmpty
	@Length(max = 255)
	private String nombre;
	
	@NotEmpty
	@Length(max = 255)
	private String codigo;
	
	@NotNull
	private Integer idCategoria;
	
	private String nombreCategoria;
	
	@NotNull
	private Integer stock;
	
	public String getDescripcion() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(codigo).append(") ")
				.append(nombre).append(" - ").append(nombreCategoria);
		return sb.toString();
	}

	@Override
	public String getIdentificador() {
		return id != null ? id.toString() : "";
	}

}
