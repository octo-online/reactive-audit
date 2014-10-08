/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.lib;

import java.lang.annotation.*;

/**
 * Annotation forcing the JVM agent to tolerate a method call to a blocking API without log or exception.
 * All blocking call from this method or from one of its callees is accepted without generating an alert.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface TolerateLatency
{
	String value() default "Tolerate a call to a blocking method from this method or its callees.";
}
