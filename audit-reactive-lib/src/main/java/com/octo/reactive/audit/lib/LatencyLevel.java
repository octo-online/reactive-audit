package com.octo.reactive.audit.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to declare a method with specific latency.
 *
 * @author Philippe PRADOS
 * @since 1.0
 * @see com.octo.reactive.audit.lib.Latency
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface LatencyLevel
{
	Latency value();
}
