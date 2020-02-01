package ar.com.clovinn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * Configuración de Thymeleaf (renderizado de pantallas)
 * 
 * @author Paula Bourlot
 *
 */

@Component
public class ThymeleafConfig {

	/**
	 * Config de carpeta por defecto de los archivos de vista
	 * 
	 * @return
	 */
	@Bean
	public ClassLoaderTemplateResolver templateResolver(){
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(1);
		return templateResolver;
	}
	
	/**
	 * Config layout
	 * 
	 * @return
	 */
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
}
