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

package com.octo.reactive.audit.javax.imageio;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.awt.image.RenderedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 5
@Aspect
public class ImageIOAudit extends AbstractFileAudit
{
	@Before("call(* javax.imageio.ImageIO.read(java.io.File))")
	public void read(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* javax.imageio.ImageIO.read(java.io.InputStream)) && args(in)")
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

	@Before("call(* javax.imageio.ImageIO.read(java.net.URL)) && args(url)")
	public void read(JoinPoint thisJoinPoint, URL url)
	{
		super.logLatency(HIGH, thisJoinPoint, URLTools.latencyURL(config, thisJoinPoint, url));
	}

	@Before("call(* javax.imageio.ImageIO.write(java.awt.image.RenderedImage, String, java.io.File))")
	public void write(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* javax.imageio.ImageIO.write(java.awt.image.RenderedImage, String, java.io.OutputStream)) && args(r,s,out)")
	public void write(JoinPoint thisJoinPoint, RenderedImage r, String s, OutputStream out)
	{
		latency(HIGH, thisJoinPoint);
		final ExceptionFactory ef = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}

}
