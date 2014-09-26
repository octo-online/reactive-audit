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

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;

// FIXME : Write unit test
public class PipedReaderTest //extends ReaderTest
{
	//@Override
	protected Reader newReader()
	{
		// TODO Pipe
		return null;
//		ConfigReactiveAudit.config.push();
//		ConfigReactiveAudit.off.commit();
//		FileInputStream in = new FileInputStream(getFileIn());
//		Reader reader=new InputStreamReader(in);
//		ConfigReactiveAudit.config.pop();
//		return new BufferedReader(reader);
	}

	@Test
	public void derived()
			throws IOException
	{
		class Derived extends OutputStreamWriter
		{
			Derived()
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		new Derived();
	}
}
