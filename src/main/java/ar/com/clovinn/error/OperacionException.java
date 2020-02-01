package ar.com.clovinn.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperacionException extends RuntimeException {
	
	private static final long serialVersionUID = -2270924427958342157L;

	public OperacionException(String mensaje){
		super(mensaje);
		log.error(mensaje);
	}

}
