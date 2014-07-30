package com.octo.reactive.audit.javax.transaction.xa;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.tools.FileObject;
import java.io.IOException;
import java.sql.SQLException;

public class XAResourceTest
{
	FileObject x = (FileObject) TestTools.createProxy(FileObject.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void delete() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.delete();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void openInputStream() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openInputStream();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void openOutputStream() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openOutputStream();
	}
	@Test(expected = NetworkAuditReactiveException.class)
	public void openReader() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openReader(true);
	}
	@Test(expected = NetworkAuditReactiveException.class)
	public void openWriter() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.openWriter();
	}
}
