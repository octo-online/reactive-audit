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

package com.octo.reactive.sample;

import com.octo.reactive.audit.lib.AssumeLatency;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.WithLatency;

/**
 * @author Yacine
 */
public class TestApp
{
    @WithLatency(Latency.MEDIUM)
    public static void withLatency()
    {
        // A method with latency
        for (int i=0;i<10000;++i) ;
    }

    @AssumeLatency
    public static void assumeLatency() throws InterruptedException
    {
        Thread.sleep(20L); // Call a tolarable method with latency
    }
	public static void main(String[] args)
			throws InterruptedException
	{
		System.out.println("Reactive test application is running...");
		Thread.sleep(200L);
        withLatency();
        assumeLatency();
		System.out.println("...and now stopped.");
	}
}
