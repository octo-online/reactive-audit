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

package com.octo.reactive.audit.org.xml.sax;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 2
@Aspect
public class InputSourceAudit extends AbstractFileAudit
{
	@Before("call(org.xml.sax.InputSource.new(java.io.InputStream)) && args(in)")
	public void read(JoinPoint thisJoinPoint, InputStream in)
	{
		final ReactiveAuditException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ex;
			}
		});
	}

	@Before("call(org.xml.sax.InputSource.new(java.io.Reader)) && args(in)")
	public void read(JoinPoint thisJoinPoint, Reader in)
	{
		final ExceptionFactory ef = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}
}
