package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by pprados on 19/05/2014.
 */
public class FileTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void createNewFile() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().createNewFile();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void createTempFile() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		File.createTempFile("prefix", "suffix");
	}

	@Test(expected = FileAuditReactiveException.class)
	public void delete() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().delete();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void exists() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().exists();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void getFreeSpace() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().getFreeSpace();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void getTotalSpace() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().getTotalSpace();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void getUsableSpace() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().getUsableSpace();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void list() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().list();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void listFiles() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().listFiles();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void mkdir() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().mkdir();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void mkdirs() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().mkdirs();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void renameTo() throws IOException
	{
		File newName = new File("toto");
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().renameTo(newName);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void setX() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		IOTestTools.getTempFile().setExecutable(true);
	}
}
