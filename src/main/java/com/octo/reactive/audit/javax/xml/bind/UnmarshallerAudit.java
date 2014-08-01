package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 4
@Aspect
public class UnmarshallerAudit extends AbstractFileAudit
{
	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.io.File))")
	public void unmarshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.io.InputStream)) && args(in)")
	public void unmarshal(JoinPoint thisJoinPoint, InputStream in)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.io.Reader)) && args(in)")
	public void unmarshal(JoinPoint thisJoinPoint, Reader in)
	{
		AuditReactiveException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.net.URL)) && args(url)")
	public void unmarshal(JoinPoint thisJoinPoint, URL url)
	{
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}
}
