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

package com.octo.reactive.audit.java.nio.file;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class FilesTest
{
	private Path getPath() throws IOException
	{
		push();
		Path f = Files.createTempFile("temp-file-name", ".tmp");
		Files.delete(f);
		//f.deleteOnExit();
		Files.createFile(f);
		pop();
		return f;
	}

	@Test(expected = FileAuditReactiveException.class)
	public void copy() throws IOException
	{
		Path path = getPath();
		Files.delete(path);
		AuditReactive.strict.commit();
		Files.copy(new ByteArrayInputStream("abc".getBytes()), path);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void readAllBytes() throws IOException
	{
		AuditReactive.off.commit();

		AuditReactive.strict.commit();
		Files.readAllBytes(getPath());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void readAllLines() throws IOException
	{
		AuditReactive.off.commit();

		AuditReactive.strict.commit();
		Files.readAllLines(getPath());
	}

	@SuppressWarnings("ConstantConditions")
    @Test(expected = FileAuditReactiveException.class)
	public void walkFileTree() throws IOException
	{
		AuditReactive.off.commit();

		AuditReactive.strict.commit();
		Path p = Paths.get(".", "");
		Files.walkFileTree(p, null, 1, null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write() throws IOException
	{
		AuditReactive.off.commit();

		AuditReactive.strict.commit();
		Files.write(getPath(), new byte[1]);
	}
}
