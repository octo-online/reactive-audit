/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.function.Supplier;

public class FileChannelTest
{
	//Jaba8 private final Supplier<FileChannel> readChannel  = IOTestTools::getInputFileChannel;
	private final Supplier<FileChannel> readChannel  =
			new Supplier<FileChannel>()
			{
				@Override
				public FileChannel get()
				{
					return IOTestTools.getInputFileChannel();
				}

			};
	//Java8 private final Supplier<FileChannel> writeChannel = IOTestTools::getOutputFileChannel;
	private final Supplier<FileChannel> writeChannel =
			new Supplier<FileChannel>()
			{
				@Override
				public FileChannel get()
				{
					return IOTestTools.getOutputFileChannel();
				}

			};

	@Test(expected = FileReactiveAuditException.class)
	public void open()
			throws IOException
	{
        TestTools.strict.commit();
		FileChannel.open(IOTestTools.getTempPath());
	}

    /*
	@Test(expected = FileReactiveAuditException.class)
	public void transferFrom()
			throws IOException
	{
		try (FileChannel s = readChannel.get();
			 FileChannel d = writeChannel.get())
		{
            TestTools.strict.commit();
			d.transferFrom(s, 0, 1);
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void transferTo()
			throws IOException
	{
		try (FileChannel s = readChannel.get();
			 FileChannel d = writeChannel.get())
		{
            TestTools.strict.commit();
			s.transferTo(0, 1, d);
		}
	}
	*/

	@Test(expected = FileReactiveAuditException.class)
	public void lock()
			throws IOException
	{
		try (FileChannel s = readChannel.get())
		{
            TestTools.strict.commit();
			s.lock();
		}
	}

}
