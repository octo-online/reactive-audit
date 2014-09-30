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

package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileTest
{
	@Test(expected = FileReactiveAuditException.class)
	public void createNewFile()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().createNewFile();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void createTempFile()
			throws IOException
	{
		TestTools.strict.commit();
		File.createTempFile("prefix", "suffix");
	}

	@Test(expected = FileReactiveAuditException.class)
	public void delete()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().delete();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void exists()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().exists();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void getFreeSpace()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().getFreeSpace();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void getTotalSpace()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().getTotalSpace();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void getUsableSpace()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().getUsableSpace();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void list()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().list();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void listFiles()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().listFiles();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void mkdir()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().mkdir();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void mkdirs()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().mkdirs();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void renameTo()
			throws IOException
	{
		File newName = new File("toto");
		TestTools.strict.commit();
		IOTestTools.getTempFile().renameTo(newName);
	}

	@Test(expected = FileReactiveAuditException.class)
	public void setX()
			throws IOException
	{
		TestTools.strict.commit();
		IOTestTools.getTempFile().setExecutable(true);
	}
}
