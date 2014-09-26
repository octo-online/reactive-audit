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

import com.octo.reactive.audit.lib.FileReactiveAuditException;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import com.octo.reactive.audit.lib.Latency;
import org.junit.Test;

public class ConsoleTest
{
	@Test(expected = ReactiveAuditException.class)
	public void readLine()
	{
		if (System.console() != null)
			System.console().readLine();
		else
			throw new FileReactiveAuditException(Latency.LOW, "No console");
	}

	@Test(expected = ReactiveAuditException.class)
	public void readLine_String()
	{
		if (System.console() != null)
			System.console().readLine("", "");
		else
			throw new FileReactiveAuditException(Latency.LOW, "No console");
	}

	@Test(expected = ReactiveAuditException.class)
	public void readPassword()
	{
		if (System.console() != null)
			System.console().readPassword();
		else
			throw new FileReactiveAuditException(Latency.LOW, "No console");
	}

	@Test(expected = ReactiveAuditException.class)
	public void readPassword_String()
	{
		if (System.console() != null)
			System.console().readPassword("", "");
		else
			throw new FileReactiveAuditException(Latency.LOW, "No console");
	}

}
