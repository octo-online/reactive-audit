package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractFileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 1
@Aspect
public class FlushableAudit extends AbstractFileAudit
{
	@Before("call(* java.io.Flushable.flush())" +
			        "&& target(java.io.FileOutputStream+) " +
			        "|| target(java.net.SocketOutputStream+)")
	public void flush(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
