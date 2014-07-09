package com.octo.reactive.audit.lib;

/**
 * The latency level.
 *
 * LOW is for open file, exist file, etc.
 * MEDIUM is for flush, close, connect, etc.
 * HIGH is for load or write data.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */
public enum Latency
{
	LOW, MEDIUM, HIGH
}

