package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class CharArrayReaderTest extends ReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		return new CharArrayReader(new char[1]);
	}
	@Test
	public void derived()
	{
		class Derived extends CharArrayReader
		{
			Derived()
			{
				super(new char[10]);
			}
		};
		new Derived();
	}
}
