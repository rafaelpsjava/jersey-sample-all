package br.com.domain.sample.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * Compress
 * 
 * if a requested jax-rs web service use this annotation the response will be
 * compress
 * 
 * @author Rafael
 *
 */
@NameBinding
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Compress {

}