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
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class InputStreamReaderTest extends AuditedReaderTest
{
	@Override
	protected Reader newReader()
			throws IOException
	{
		push();
		InputStream in = new FileInputStream(IOTestTools.getTempFile());
		pop();
		return new InputStreamReader(in);
	}

	@Test(expected = FileReactiveAuditException.class)
	public void read_with_FilterInputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Reader in = new InputStreamReader(new BufferedInputStream(new FileInputStream(IOTestTools.getTempFile()))))
		{
            TestTools.strict.commit();
			in.read();
		}
	}
}
