package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.FileOutputStream;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 1
@Aspect
public class FlushableAudit extends AbstractFileAudit
{
	@Before("call(* java.io.Flushable.flush())")
	public void flush(JoinPoint thisJoinPoint)
	{
		if ((thisJoinPoint.getTarget() instanceof FileOutputStream) ||
			(thisJoinPoint.getTarget().getClass().getName().equals("java.net.SocketOutputStream")))
			latency(HIGH, thisJoinPoint);
	}

}
