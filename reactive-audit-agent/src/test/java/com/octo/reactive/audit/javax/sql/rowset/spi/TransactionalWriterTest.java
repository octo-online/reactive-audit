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

package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import javax.sql.rowset.spi.TransactionalWriter;
import java.sql.SQLException;

public class TransactionalWriterTest
{
	private final TransactionalWriter tw = (TransactionalWriter) TestTools.createProxy(TransactionalWriter.class);

	@Test(expected = NetworkReactiveAuditException.class)
	public void commit()
			throws SQLException
	{
        TestTools.strict.commit();
		tw.commit();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void rollback()
			throws SQLException
	{
        TestTools.strict.commit();
		tw.rollback();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void rollback_SavePoint()
			throws SQLException
	{
        TestTools.strict.commit();
		tw.rollback(null);
	}
}
