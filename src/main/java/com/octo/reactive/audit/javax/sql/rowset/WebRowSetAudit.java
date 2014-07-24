package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.NetworkAudit;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.ResultSet;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class WebRowSetAudit extends NetworkAudit
{
	@Before("call(* javax.sql.rowset.WebRowSet.readXml(java.io.InputStream)) && args(in)")
	public void commit(JoinPoint thisJoinPoint, InputStream in)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.readXml(java.io.Reader)) && args(in)")
	public void commit(JoinPoint thisJoinPoint, Reader in)
	{
		AuditReactiveException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.io.OutputStream)) && args(out)")
	public void commit(JoinPoint thisJoinPoint, OutputStream out)
	{
		AuditReactiveException ex = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.sql.ResultSet,java.io.OutputStream)) && args(r,out)")
	public void commit(JoinPoint thisJoinPoint, ResultSet rs, OutputStream out)
	{
		AuditReactiveException ex = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.sql.ResultSet,java.io.Writer)) && args(r,out)")
	public void commit(JoinPoint thisJoinPoint, ResultSet rs, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.io.Writer)) && args(out)")
	public void commit(JoinPoint thisJoinPoint, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

}
