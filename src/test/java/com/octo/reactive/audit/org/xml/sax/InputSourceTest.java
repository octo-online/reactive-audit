package com.octo.reactive.audit.org.xml.sax;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class InputSourceTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void new_InputStream() throws IOException
	{
		AuditReactive.strict.commit();
		new InputSource(new FileInputStream(IOTestTools.getTempFile()));
	}

	@Test(expected = FileAuditReactiveException.class)
	public void new_Reader() throws IOException
	{
		AuditReactive.strict.commit();
		new InputSource(new FileReader(IOTestTools.getTempFile()));
	}


}
