package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
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
        AuditReactive.strict.commit();
        try (OutputStream in=newOutputStream())
        {
           AuditReactive.off.commit();
        }
    }
    @Test
    public void flush() throws IOException
    {
        AuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            AuditReactive.strict.commit();
            out.flush();
        }
    }
    @Test
    public void close() throws IOException
    {
        AuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            AuditReactive.strict.commit();
            out.close();
        }
    }
    @Test
    public void write_b() throws IOException
    {
        AuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            AuditReactive.strict.commit();
            out.write((byte) 1);
        }
    }
    @Test
    public void write_B() throws IOException
    {
        AuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            AuditReactive.strict.commit();
            out.write(new byte[1]);
        }
    }
    @Test
    public void write_Bii() throws IOException
    {
        AuditReactive.off.commit();
        try (OutputStream out=newOutputStream())
        {
            AuditReactive.strict.commit();
            out.write(new byte[1],0,1);
        }
    }
}
