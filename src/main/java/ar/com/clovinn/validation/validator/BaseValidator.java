package ar.com.clovinn.validation.validator;

import ar.com.clovinn.dto.BaseDto;

public class BaseValidator {

	protected boolean esValido(BaseDto baseDto, Object identificador) {
		return baseDto == null || (identificador != null && 
				baseDto.getIdentificador().equals(identificador));
	}

}
