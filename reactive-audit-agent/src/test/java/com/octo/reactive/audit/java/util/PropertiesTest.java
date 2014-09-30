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

package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class PropertiesTest
{
	@Test(expected = ReactiveAuditException.class)
	public void load_FileInputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		InputStream in = new FileInputStream(IOTestTools.getTempFile());
        TestTools.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test(expected = ReactiveAuditException.class)
	public void load_FileReader()
			throws IOException
	{
		ReactiveAudit.off.commit();
		Reader in = new FileReader(IOTestTools.getTempFile());
        TestTools.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test
	public void load_InputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		InputStream in = new ByteArrayInputStream(new byte[1]);
        TestTools.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test
	public void load_Reader()
			throws IOException
	{
		ReactiveAudit.off.commit();
		Reader in = new StringReader("");
        TestTools.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}
}
