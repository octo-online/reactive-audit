package com.octo.reactive.audit.java.nio.file;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 19/05/2014.
 */
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
		ConfigAuditReactive.strict.commit();
		Files.copy(new ByteArrayInputStream("abc".getBytes()), path);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void readAllBytes() throws IOException
	{
		ConfigAuditReactive.off.commit();

		ConfigAuditReactive.strict.commit();
		Files.readAllBytes(getPath());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void readAllLines() throws IOException
	{
		ConfigAuditReactive.off.commit();

		ConfigAuditReactive.strict.commit();
		Files.readAllLines(getPath());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void walkFileTree() throws IOException
	{
		ConfigAuditReactive.off.commit();

		ConfigAuditReactive.strict.commit();
		Path p = Paths.get(".", "");
		Files.walkFileTree(p, null, 1, null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write() throws IOException
	{
		ConfigAuditReactive.off.commit();

		ConfigAuditReactive.strict.commit();
		Files.write(getPath(), new byte[1]);
	}
}
