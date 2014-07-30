package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.NetworkAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.sql.rowset.WebRowSet;
import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class XMLWriterAudit extends NetworkAudit
{
	@Before("call(* javax.sql.rowset.spi.XMLWriter.writeXml(javax.sql.rowset.WebRowSet,java.io.Writer)) && args(wrs,out)")
	public void commit(JoinPoint thisJoinPoint, WebRowSet wrs, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}
}
