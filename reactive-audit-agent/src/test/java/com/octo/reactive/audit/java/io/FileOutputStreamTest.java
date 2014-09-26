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

package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamTest extends AuditOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream()
			throws IOException
	{
		return new FileOutputStream(IOTestTools.getTempFile());
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void New()
			throws IOException
	{
		super.New();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void derived()
			throws IOException
	{
		class Derived extends FileOutputStream
		{
			Derived()
					throws IOException
			{
				super(IOTestTools.getTempFile());
			}
		}
		new Derived();
	}
}
