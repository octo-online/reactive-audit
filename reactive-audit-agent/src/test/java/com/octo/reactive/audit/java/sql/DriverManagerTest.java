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

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerTest
{
	private static final String DB = "jdbc:derby:memory:myDB;create=true";

	private static Connection getDBConnection()
			throws SQLException
	{
		return DriverManager.getConnection(DB, "SA", "");
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getConnection()
			throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestTools.strict.commit();
		getDBConnection();
	}
}
