package ar.com.clovinn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import ar.com.clovinn.form.BaseForm;

public class BaseController {
	
	protected static final String CODIGO_MENSAJE_CAMBIOS_APLICADOS = "general.cambios.aplicados";
	protected static final String CODIGO_MENSAJE_ERROR_INTEGRIDAD = "general.error.integridad";
	protected static final int TAMANYO_PAGINA = 15;
	protected static final int TAMANYO_PAGINA_REST = 20;
	protected static final String REDIRECCION_INDEX = "redirect:/";
	
	@Autowired
	protected MessageSource messageSource;

	protected void agregarMensajeExitoPorCodigo(BaseForm form, String codigoMensaje) {
		String mensaje = messageSource.getMessage(codigoMensaje, null, null);
		form.setMensajeExito(mensaje);
	}
	
	protected void agregarMensajeErrorPorCodigo(BaseForm form, String codigoMensaje) {
		String mensaje = messageSource.getMessage(codigoMensaje, null, null);
		form.setMensajeError(mensaje);
	}
	
	protected void agregarMensajeError(BaseForm form, String mensaje) {
		form.setMensajeError(mensaje);
	}
	
	protected void agregarMensajeExito(BaseForm form, String mensaje) {
		form.setMensajeExito(mensaje);
	}
	
}
