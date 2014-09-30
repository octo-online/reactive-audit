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

package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.CPUReactiveAuditException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorServiceTest
{
	final private ExecutorService e = Executors.newFixedThreadPool(1);

	@Test(expected = CPUReactiveAuditException.class)
	public void awaitTermination()
			throws InterruptedException
	{
		TestTools.strict.commit();
		e.awaitTermination(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void invokeAny()
			throws InterruptedException, ExecutionException, TimeoutException
	{
		TestTools.strict.commit();
		e.invokeAny(new ArrayList<Callable<Object>>(), 1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void shutdownNow()
			throws InterruptedException
	{
		TestTools.strict.commit();
		e.shutdownNow();
	}
}
