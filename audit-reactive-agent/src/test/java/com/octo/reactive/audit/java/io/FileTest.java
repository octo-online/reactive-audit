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

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void createNewFile() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().createNewFile();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void createTempFile() throws IOException
	{
		AuditReactive.strict.commit();
		File.createTempFile("prefix", "suffix");
	}

	@Test(expected = FileAuditReactiveException.class)
	public void delete() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().delete();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void exists() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().exists();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void getFreeSpace() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().getFreeSpace();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void getTotalSpace() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().getTotalSpace();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void getUsableSpace() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().getUsableSpace();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void list() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().list();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void listFiles() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().listFiles();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void mkdir() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().mkdir();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void mkdirs() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().mkdirs();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void renameTo() throws IOException
	{
		File newName = new File("toto");
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().renameTo(newName);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void setX() throws IOException
	{
		AuditReactive.strict.commit();
		IOTestTools.getTempFile().setExecutable(true);
	}
}
