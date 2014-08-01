package com.octo.reactive.audit.java.rmi.registry;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RegistryTest
{
	private static final int RMIPORT = 1099;

	@Test(expected = NetworkAuditReactiveException.class)
	public void lookup() throws NotBoundException, RemoteException
	{
		AuditReactive.strict.commit();
		LocateRegistry.createRegistry(RMIPORT);
		LocateRegistry.getRegistry().lookup("abc");
	}

}
