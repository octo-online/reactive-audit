package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.aspectj.lang.JoinPoint;

/**
 * Created by pprados on 13/06/2014.
 */
public class NetworkAudit extends AbstractAudit
{
	@Override
	protected AuditReactiveException newException(JoinPoint thisJoinPoint)
	{
		return new NetworkAuditReactiveException(thisJoinPoint.getSignature().toString());
	}
}
