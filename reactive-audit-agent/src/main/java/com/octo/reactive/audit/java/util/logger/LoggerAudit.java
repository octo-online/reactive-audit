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

package com.octo.reactive.audit.java.util.logger;

import com.octo.reactive.audit.AbstractCPUAudit;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// Remove audit for logger
@Aspect
public class LoggerAudit extends AbstractCPUAudit
{
	@Before("execution(* java.util.logger.Logger.*(..))")
	public void beforeLogger(JoinPoint thisJoinPoint)
	{
		ReactiveAudit.config.incSuppress();
	}

	@After("execution(* java.util.logger.Logger.*(..))")
	public void afterLogger(JoinPoint thisJoinPoint)
			throws ReactiveAuditException
	{
		ReactiveAudit.config.decSuppress();
	}
}
