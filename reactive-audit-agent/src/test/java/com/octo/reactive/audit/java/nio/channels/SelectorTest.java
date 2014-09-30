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

import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Selector;

public class SelectorTest
{
	@Test(expected = NetworkReactiveAuditException.class)
	public void select()
			throws IOException
	{
		ReactiveAudit.config.begin().debug(true).commit();
		try (Selector r = Selector.open())
		{
            TestTools.strict.commit();
			r.select();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void select_l()
			throws IOException
	{
		ReactiveAudit.config.begin().debug(true).commit();
		try (Selector r = Selector.open())
		{
            TestTools.strict.commit();
			r.select(100);
		}
	}
}
