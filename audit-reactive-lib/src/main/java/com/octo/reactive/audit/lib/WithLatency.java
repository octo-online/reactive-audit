package com.octo.reactive.audit.lib;

import java.lang.annotation.*;

/**
 * Annotation to declare a specific method has latency.
 * A call of this method can generate a log or throw an {@link AuditReactiveException}
 * if the audit agent is used.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface WithLatency
{
	/**
	 * The latency level for this method.
	 * @return The current latency specified for this method.
	 */
	Latency value() default Latency.HIGH;

	String msg() default "This method can not be called with a reactive thread.";
}
