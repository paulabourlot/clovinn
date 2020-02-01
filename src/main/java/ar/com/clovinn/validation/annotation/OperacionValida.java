package ar.com.clovinn.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ar.com.clovinn.validation.validator.ProductoOperacionValidator;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ProductoOperacionValidator.class })
@Documented
public @interface OperacionValida {

	String message() default "{validation.error}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
}

