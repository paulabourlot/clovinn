package ar.com.clovinn.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductoException extends RuntimeException {
	
	private static final long serialVersionUID = -2270924427958342157L;

	public ProductoException(String mensaje){
		super(mensaje);
		log.error(mensaje);
	}

}
