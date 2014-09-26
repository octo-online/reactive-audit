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

package com.octo.reactive.audit.javax.tools;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.tools.FileObject;
import java.io.IOException;
import java.sql.SQLException;

public class FileObjectTest
{
	private final FileObject x = (FileObject) TestTools.createProxy(FileObject.class);

	@Test(expected = FileAuditReactiveException.class)
	public void delete()
	{
		AuditReactive.strict.commit();
		x.delete();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openInputStream()
			throws IOException
	{
		AuditReactive.strict.commit();
		x.openInputStream();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openOutputStream()
			throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openOutputStream();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openReader()
			throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openReader(true);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openWriter()
			throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openWriter();
	}
}
