package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by pprados on 06/05/14.
 */
public abstract class OutputStreamTest
{
	protected abstract OutputStream newOutputStream() throws IOException;

    @Test
    public void New() throws IOException
    {
        ConfigAuditReactive.strict.commit();
        try (OutputStream in=newOutputStream())
        {
           ConfigAuditReactive.off.commit();
        }
    }
    @Test
    public void flush() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            ConfigAuditReactive.strict.commit();
            out.flush();
        }
    }
    @Test
    public void close() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            ConfigAuditReactive.strict.commit();
            out.close();
        }
    }
    @Test
    public void write_b() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            ConfigAuditReactive.strict.commit();
            out.write((byte) 1);
        }
    }
    @Test
    public void write_B() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            ConfigAuditReactive.strict.commit();
            out.write(new byte[1]);
        }
    }
    @Test
    public void write_Bii() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            ConfigAuditReactive.strict.commit();
            out.write(new byte[1],0,1);
        }
    }
}
