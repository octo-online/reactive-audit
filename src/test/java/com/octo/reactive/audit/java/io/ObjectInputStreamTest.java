package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
public class ObjectInputStreamTest extends AuditedInputStreamTest
{

	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new ObjectInputStream(new FileInputStream(getFileIn()));
	}
	private File getFileIn() throws IOException
	{
		push();
		File f=File.createTempFile("temp-file-name", ".tmp");
		f.delete();
		f.deleteOnExit();
		f.createNewFile();
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject("");
		out.close();
		pop();
		return f;
	}
	@Test
	public void derived() throws IOException
	{
		class Derived extends ObjectInputStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayInputStream(new byte[10]));
			}
		};
		new Derived();
	}

}
