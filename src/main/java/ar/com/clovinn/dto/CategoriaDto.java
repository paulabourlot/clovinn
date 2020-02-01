package ar.com.clovinn.dto;

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
public class CategoriaDto implements BaseDto {

	private Integer id;
	
	private String nombre;
	
	@Override
	public String getIdentificador() {
		return id != null ? id.toString() : "";
	}

}
