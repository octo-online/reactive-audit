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

package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 10
@Aspect
public class ResultSetAudit extends AbstractNetworkAudit
{
	@Before("call(boolean java.sql.ResultSet.absolute(int))")
	public void absolute(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.afterLast())")
	public void afterLast(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.beforeFirst())")
	public void beforeFirst(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.deleteRow())")
	public void deleteRow(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.first())")
	public void first(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.last())")
	public void last(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.next())")
	public void next(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.previous())")
	public void previous(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.refreshRow())")
	public void refreshRow(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.relative(int))")
	public void relative(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
