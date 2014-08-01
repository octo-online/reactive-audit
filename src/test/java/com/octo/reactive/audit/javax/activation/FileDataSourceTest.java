package com.octo.reactive.audit.javax.activation;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.activation.FileDataSource;

public class FileDataSourceTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void new_File()
	{
		AuditReactive.strict.commit();
		new FileDataSource(IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void new_String()
	{
		AuditReactive.strict.commit();
		new FileDataSource(IOTestTools.getTempFile().getAbsolutePath());
	}
}
