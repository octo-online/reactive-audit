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

package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.junit.Test;

import javax.sql.rowset.WebRowSet;
import java.io.IOException;
import java.sql.SQLException;

public class WebRowSetTest
{
	private final WebRowSet x = (WebRowSet) TestTools.createProxy(WebRowSet.class);

	@Test(expected = ReactiveAuditException.class)
	public void readXML_InputStream()
			throws IOException, SQLException
	{
		TestTools.strict.commit();
		x.readXml(IOTestTools.getTempFileInputStream());
	}

	@Test(expected = ReactiveAuditException.class)
	public void readXML_Reader()
			throws SQLException
	{
		TestTools.strict.commit();
		x.readXml(IOTestTools.getTempFileReader());
	}

	@Test(expected = ReactiveAuditException.class)
	public void writeXML_OutputStream()
			throws IOException, SQLException
	{
		TestTools.strict.commit();
		x.writeXml(IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = ReactiveAuditException.class)
	public void writeXML_RS_OutputStream()
			throws IOException, SQLException
	{
		TestTools.strict.commit();
		x.writeXml(null, IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = ReactiveAuditException.class)
	public void writeXML_Writer()
			throws SQLException
	{
		TestTools.strict.commit();
		x.writeXml(IOTestTools.getTempFileWriter());
	}

	@Test(expected = ReactiveAuditException.class)
	public void writeXML_RS_Writer()
			throws SQLException
	{
		TestTools.strict.commit();
		x.writeXml(null, IOTestTools.getTempFileWriter());
	}
}
