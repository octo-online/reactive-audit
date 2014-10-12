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
 * Annotation to declare that a specific method has latency.
 * A call of this method can generate a log or throw a {@link ReactiveAuditException}
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
	 *
	 * @return The current latency specified for this method.
	 */
	Latency value() default Latency.HIGH;

	String msg() default "This method can not be called with a reactive thread.";
}
