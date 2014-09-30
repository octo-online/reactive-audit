/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;

@SuppressWarnings("ExternalizableWithoutPublicNoArgConstructor")
public class RemoteRefTest
{
	@Test(expected = NetworkReactiveAuditException.class)
	@SuppressWarnings("deprecation")
	public void invoke()
			throws Exception
	{
        TestTools.strict.commit();
		RemoteRef ref = new RemoteRef()
		{

			@Override
			public Object invoke(Remote obj, Method method, Object[] params, long opnum)
					throws Exception
			{
				return null;
			}

			@Override
			public java.rmi.server.RemoteCall newCall(RemoteObject obj, java.rmi.server.Operation[] op, int opnum,
													  long hash)
					throws RemoteException
			{
				return null;
			}

			@Override
			public void invoke(java.rmi.server.RemoteCall call)
					throws Exception
			{

			}

			@Override
			public void done(java.rmi.server.RemoteCall call)
					throws RemoteException
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
			public void writeExternal(ObjectOutput objectOutput)
					throws IOException
			{

			}

			@Override
			public void readExternal(ObjectInput objectInput)
					throws IOException, ClassNotFoundException
			{

			}
		};
		ref.invoke(null, null, null, 0);
	}

}
