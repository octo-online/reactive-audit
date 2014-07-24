package com.octo.reactive.audit.java.io;

import java.io.*;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
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
		};
		return new ExFilter();
	}
}
