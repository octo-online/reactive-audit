package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.OutputStream;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 3
@Aspect
public class OutputStreamAudit extends AbstractOutputStreamAudit
{
	@Before("call(* java.io.OutputStream.write(..))")
	public void write(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			OutputStream out = (OutputStream) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(out));
		}
		latency(HIGH, thisJoinPoint, (OutputStream) thisJoinPoint.getTarget());
	}

}
