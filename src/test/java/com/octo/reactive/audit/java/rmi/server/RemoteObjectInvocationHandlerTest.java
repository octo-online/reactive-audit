package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;

public class RemoteObjectInvocationHandlerTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void invoke() throws Throwable
	{
		AuditReactive.strict.commit();
		RemoteRef ref = new RemoteRef()
		{

			@Override
			public Object invoke(Remote obj, Method method, Object[] params, long opnum) throws Exception
			{
				return null;
			}

			@Override
			public RemoteCall newCall(RemoteObject obj, Operation[] op, int opnum, long hash) throws RemoteException
			{
				return null;
			}

			@Override
			public void invoke(RemoteCall call) throws Exception
			{

			}

			@Override
			public void done(RemoteCall call) throws RemoteException
			{

			}

			@Override
			public String getRefClass(ObjectOutput out)
			{
				return null;
			}

			@Override
			public int remoteHashCode()
			{
				return 0;
			}

			@Override
			public boolean remoteEquals(RemoteRef obj)
			{
				return false;
			}

			@Override
			public String remoteToString()
			{
				return null;
			}

			@Override
			public void writeExternal(ObjectOutput objectOutput) throws IOException
			{

			}

			@Override
			public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException
			{

			}
		};
		RemoteObjectInvocationHandler handler = new RemoteObjectInvocationHandler(ref);
		handler.invoke(null, null, null);
	}

}
