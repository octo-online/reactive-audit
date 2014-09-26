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

package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementTest
{
	private final PreparedStatement stm = (PreparedStatement) TestTools.createProxy(PreparedStatement.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute()
			throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.execute();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void executeQuery()
			throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.executeQuery();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void executeLargeUpdate()
			throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.executeLargeUpdate();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void executeUpdate()
			throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.executeUpdate();
	}
}
