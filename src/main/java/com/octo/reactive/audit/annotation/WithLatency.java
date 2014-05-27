package com.octo.reactive.audit.annotation;

import com.octo.reactive.audit.Latency;

import java.lang.annotation.*;

import static com.octo.reactive.audit.Latency.HIGH;

/**
 * Annotation to declare a specific method has latency.
 * A call of this method can generate a log or throw an AuditReactiveException
 * if the agent is used.
 * This annotation can detect a latency call in a higger level.
 *
 * TODO: documenter values
 *
 * @author Philippe PRADOS
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface WithLatency
{
	Latency value() default HIGH;

	String msg() default "This method can not be called with reactive thread.";
}
