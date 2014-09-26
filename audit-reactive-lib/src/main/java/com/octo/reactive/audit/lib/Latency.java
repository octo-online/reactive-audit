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

package com.octo.reactive.audit.lib;

/**
 * The latency level.
 * <p>
 * <b>LOW</b> is for open file, file exists, etc.
 * <b>MEDIUM</b> is for flush, close, connect, etc.
 * <b>HIGH</b> is for load or write data.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */
public enum Latency
{
	LOW, MEDIUM, HIGH
}

