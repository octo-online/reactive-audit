package com.octo.reactive.audit;

import java.lang.annotation.*;

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
	int value() default LatencyLevel.HIGH;
	String msg() default "Use latency method";
}
