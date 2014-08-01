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
	public void openInputStream() throws IOException
	{
		AuditReactive.strict.commit();
		x.openInputStream();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openOutputStream() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openOutputStream();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openReader() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openReader(true);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openWriter() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openWriter();
	}
}
