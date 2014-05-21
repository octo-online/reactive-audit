package com.octo.reactive.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by pprados on 07/05/14.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface LatencyLevel
{
//    static final int LOW  = 10;
//    static final int MEDIUM  = 50;
//    static final int HIGH = 100;

	Latency value();
}
