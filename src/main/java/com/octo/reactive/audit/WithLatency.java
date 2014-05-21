package com.octo.reactive.audit;

import java.lang.annotation.*;

import static com.octo.reactive.audit.Latency.HIGH;

/**
 * Created by pprados on 07/05/2014.
 */
// FIXME: API: Faut-il avoir level en default ou msg ?
// FIXME: dans le package client

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface WithLatency
{
	Latency value() default HIGH;

	String msg() default "Use latency method";
}
