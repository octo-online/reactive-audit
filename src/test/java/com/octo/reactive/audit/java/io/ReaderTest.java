package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public abstract class ReaderTest
{
	protected abstract Reader newReader() throws IOException;

	@Test
	public void New() throws IOException
	{
		AuditReactive.strict.commit();
		try (Reader reader = newReader())
		{
			AuditReactive.off.commit();
		}

	}

	@Test
	public void close() throws IOException
	{
		AuditReactive.off.commit();
		try (Reader in = newReader())
		{
			AuditReactive.strict.commit();
			in.close();
		}
	}

	@Test
	public void read() throws IOException
	{
		AuditReactive.off.commit();
		try (Reader in = newReader())
		{
			AuditReactive.strict.commit();
			in.read();
		}
	}

	@Test
	public void read_C() throws IOException
	{
		AuditReactive.off.commit();
		try (Reader in = newReader())
		{
			AuditReactive.strict.commit();
			in.read(new char[1]);
		}
	}

	@Test
	public void read_Cii() throws IOException
	{
		AuditReactive.off.commit();
		try (Reader in = newReader())
		{
			AuditReactive.strict.commit();
			in.read(new char[1], 0, 1);
		}
	}

	//	@Test TODO CharBuffer
//	public void read_CharBuffer() throws IOException
//	{
//		CharBuffer buffer=new CharBuffer();
//		ConfigAuditReactive.off.commit();
//		try (Reader in= newReader())
//		{
//			ConfigAuditReactive.strict.commit();
//			in.read(new byte[1], 0, 1);
//		}
//	}
	@Test
	public void skip() throws IOException
	{
		AuditReactive.off.commit();
		try (Reader in = newReader())
		{
			AuditReactive.strict.commit();
			in.skip(0);
		}
	}

}
