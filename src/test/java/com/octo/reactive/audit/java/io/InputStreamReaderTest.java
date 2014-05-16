package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;
import java.nio.CharBuffer;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
public class InputStreamReaderTest extends AuditedReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		push();
		InputStream in=new FileInputStream(getFileIn());
		pop();
		return new InputStreamReader(in);
	}
	protected File getFileIn() throws IOException
	{
		push();
		File f=File.createTempFile("temp-file-name", ".tmp");
		f.delete();
		f.deleteOnExit();
		f.createNewFile();
		pop();
		return f;
	}
	// FIXME: est-ce ncessaire ?
	@Test(expected=AuditReactiveException.class)
	public void read_with_FilterInputStream() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Reader in= new InputStreamReader(new BufferedInputStream(new FileInputStream(getFileIn()))))
		{
			ConfigAuditReactive.strict.commit();
			in.read();
		}
	}
}
