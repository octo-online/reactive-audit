package com.octo.reactive.audit.lib;

import java.lang.annotation.*;

/**
 * Annotation to accept to call a blocking API without log or exception.
 * All call from this method or from method called by this method was accepted without alert.
 *
 * @authors Philippe PRADOS
 * <p>
 * TODO: documenter values
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface SuppressAuditReactive
{
	String value() default "Assume a call to a blocking method after this point.";
}
