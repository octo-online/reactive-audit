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
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterTest extends OutputStreamWriterTest
{
	@SuppressWarnings("RefusedBequest")
	@Override
	protected Writer newWriter()
			throws IOException
	{
		return new FileWriter(IOTestTools.getTempFile());
	}

	@SuppressWarnings("RedundantMethodOverride")
	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void derived()
			throws IOException
	{
		class Derived extends FileWriter
		{
			Derived()
					throws IOException
			{
				super(IOTestTools.getTempFile());
			}
		}
		new Derived();
	}

	@Test(expected = ReactiveAuditException.class)
	@Override
	public void New()
			throws IOException
	{
		super.New();
	}
}
