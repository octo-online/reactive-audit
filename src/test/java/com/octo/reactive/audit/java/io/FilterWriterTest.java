package com.octo.reactive.audit.java.io;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class FilterWriterTest extends OutputStreamWriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		push();
		final Writer outWriter = super.newWriter();
		pop();
		class ExFilter extends FilterWriter
		{
			ExFilter()
			{
				super(outWriter);
			}
		}
		return new ExFilter();
	}
}
