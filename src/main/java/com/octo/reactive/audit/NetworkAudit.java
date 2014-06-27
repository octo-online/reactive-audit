package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;

/**
 * Created by pprados on 13/06/2014.
 */
public class NetworkAudit extends AbstractAudit
{
	@Override
	protected AuditReactiveException newException(JoinPoint thisJoinPoint)
	{
		return FactoryException.newNetwork(thisJoinPoint);
	}
}
