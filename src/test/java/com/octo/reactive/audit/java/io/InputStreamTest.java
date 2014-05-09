package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pprados on 06/05/14.
 */
public abstract class InputStreamTest
{
	protected abstract InputStream newInputStream() throws IOException;

    @Test
    public void New() throws IOException
    {
        ConfigAuditReactive.strict.commit();
        try (InputStream in= newInputStream())
        {
	        ConfigAuditReactive.off.commit();
        }
    }

    @Test
    public void available() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (InputStream in= newInputStream())
        {
            ConfigAuditReactive.strict.commit();
            in.available();
        }
    }
    @Test
    public void close() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (InputStream in= newInputStream())
        {
            ConfigAuditReactive.strict.commit();
            in.close();
        }
    }
    @Test
    public void read() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (InputStream in= newInputStream())
        {
            ConfigAuditReactive.strict.commit();
            in.read();
        }
    }
    @Test
    public void read_B() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (InputStream in= newInputStream())
        {
            ConfigAuditReactive.strict.commit();
            in.read(new byte[1]);
        }
    }
    @Test
    public void read_Bii() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (InputStream in= newInputStream())
        {
            ConfigAuditReactive.strict.commit();
            in.read(new byte[1], 0, 1);
        }
    }
    @Test
    public void skip() throws IOException
    {
        ConfigAuditReactive.off.commit();
        try (InputStream in= newInputStream())
        {
            ConfigAuditReactive.strict.commit();
            in.skip(0);
        }
    }
}
