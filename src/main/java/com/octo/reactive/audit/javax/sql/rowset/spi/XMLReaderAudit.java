package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.NetworkAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.sql.rowset.WebRowSet;
import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class XMLReaderAudit extends NetworkAudit
{
	@Before("call(* javax.sql.rowset.spi.WebRowSet.readXml(javax.sql.rowset.WebRowSet,java.io.Reader)) && args(wrs,in)")
	public void commit(JoinPoint thisJoinPoint, WebRowSet wrs, Reader in)
	{
		AuditReactiveException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}
}
