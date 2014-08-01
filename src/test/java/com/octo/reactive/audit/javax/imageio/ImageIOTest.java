package com.octo.reactive.audit.javax.imageio;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ImageIOTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void read_File() throws IOException
	{
		AuditReactive.strict.commit();
		ImageIO.read(IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void read_InputStream() throws IOException
	{
		AuditReactive.strict.commit();
		ImageIO.read(IOTestTools.getTempFileInputStream());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void read_URL() throws IOException
	{
		AuditReactive.strict.commit();
		ImageIO.read(IOTestTools.getTempFile().toURI().toURL());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_File() throws IOException
	{
		AuditReactive.strict.commit();
		ImageIO.write(null, "", IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_OutputStream() throws IOException
	{
		AuditReactive.strict.commit();
		ImageIO.write(null, "", IOTestTools.getTempFileOutputStream());
	}
}
