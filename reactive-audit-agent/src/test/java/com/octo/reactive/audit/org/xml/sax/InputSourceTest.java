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

package com.octo.reactive.audit.org.xml.sax;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class InputSourceTest
{
	@Test(expected = FileReactiveAuditException.class)
	public void new_InputStream()
			throws IOException
	{
        TestTools.strict.commit();
		new InputSource(new FileInputStream(IOTestTools.getTempFile()));
	}

	@Test(expected = FileReactiveAuditException.class)
	public void new_Reader()
			throws IOException
	{
        TestTools.strict.commit();
		new InputSource(new FileReader(IOTestTools.getTempFile()));
	}


}
