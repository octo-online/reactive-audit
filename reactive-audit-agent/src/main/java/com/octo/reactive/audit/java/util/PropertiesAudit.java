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

package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 2
@Aspect
public class PropertiesAudit extends AbstractFileAudit
{
	@Before("call(* java.util.Properties.load(java.io.InputStream)) && args(in)")
	public void advice_high(JoinPoint thisJoinPoint, InputStream in)
	{
		if (FileTools.isLastInputStreamWithLatency(in) != 0)
			latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.util.Properties.load(java.io.Reader)) && args(in)")
	public void advice_high(JoinPoint thisJoinPoint, Reader in)
	{
		if (FileTools.isLastReaderWithLatency(in) != 0)
			latency(HIGH, thisJoinPoint);
	}
}
