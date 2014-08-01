package com.octo.reactive.audit.javax.rmi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class PortableRemoteObjectTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws RemoteException
	{
		AuditReactive.strict.commit();
		PortableRemoteObject.connect(null, null);
	}
}
