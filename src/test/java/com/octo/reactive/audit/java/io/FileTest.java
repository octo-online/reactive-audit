package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 19/05/2014.
 */
public class FileTest
{
	private File getFileIn() throws IOException
	{
		push();
		File f=File.createTempFile("temp-file-name", ".tmp");
		f.delete();
		f.deleteOnExit();
		f.createNewFile();
		pop();
		return f;
	}

	@Test(expected=AuditReactiveException.class)
	public void createNewFile() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().createNewFile();
	}
	@Test(expected=AuditReactiveException.class)
	public void createTempFile() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		File.createTempFile("prefix","suffix");
	}
	@Test(expected=AuditReactiveException.class)
	public void delete() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().delete();
	}
	@Test(expected=AuditReactiveException.class)
	public void exists() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().exists();
	}
	@Test(expected=AuditReactiveException.class)
	public void getFreeSpace() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().getFreeSpace();
	}
	@Test(expected=AuditReactiveException.class)
	public void getTotalSpace() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().getTotalSpace();
	}
	@Test(expected=AuditReactiveException.class)
	public void getUsableSpace() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().getUsableSpace();
	}
	@Test(expected=AuditReactiveException.class)
	public void list() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().list();
	}
	@Test(expected=AuditReactiveException.class)
	public void listFiles() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().listFiles();
	}
	@Test(expected=AuditReactiveException.class)
	public void mkdir() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().mkdir();
	}
	@Test(expected=AuditReactiveException.class)
	public void mkdirs() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().mkdirs();
	}
	@Test(expected=AuditReactiveException.class)
	public void renameTo() throws IOException
	{
		File newName=new File("toto");
		ConfigAuditReactive.strict.commit();
		getFileIn().renameTo(newName);
	}
	@Test(expected=AuditReactiveException.class)
	public void setX() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		getFileIn().setExecutable(true);
	}
}
