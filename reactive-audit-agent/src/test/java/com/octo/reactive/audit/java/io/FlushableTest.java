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
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.Flushable;
import java.io.IOException;

public class FlushableTest
{
	@Test
	public void flush_1()
			throws IOException
	{
        TestTools.strict.commit();
		new Flushable()
		{
			@Override
			public void flush()
			{

			}
		}.flush();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void flush_2()
			throws IOException
	{
        TestTools.strict.commit();
		IOTestTools.getTempFileOutputStream().flush();
	}
}
