package com.octo.reactive.audit.org.xml.sax;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 2
@Aspect
public class InputSourceAudit extends AbstractFileAudit
{
	@Before("call(org.xml.sax.InputSource.new(java.io.InputStream)) && args(in)")
	public void read(JoinPoint thisJoinPoint, InputStream in)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(org.xml.sax.InputSource.new(java.io.Reader)) && args(in)")
	public void read(JoinPoint thisJoinPoint, Reader in)
	{
		AuditReactiveException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}
}
