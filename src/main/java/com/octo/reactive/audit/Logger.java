package com.octo.reactive.audit;

/**
 * Created by pprados on 07/05/14.
 */
public class Logger
{
    public static final int None  = 0;// FIXME: 0;
    public static final int Error = 1;
    public static final int Warn  = 2;
    public static final int Info  = 3;
    private             int level = None;

    void setLevel(int aLevel)
    {
        level = aLevel;
    }

    public int getLogLevel()
    {
        return level;
    }

    public void error(Object msg)
    {
        if (level >= Error)
            log_(msg.toString());
    }

    public void error(String format, String... args)
    {
        if (level >= Error)
            log_(String.format(format, args));
    }

    public void warn(Object msg)
    {
        if (level >= Warn)
            log_(msg.toString());
    }

    public void warn(String format, String... args)
    {
        if (level >= Warn)
            log_(String.format(format, args));
    }

    public void info(Object msg)
    {
        if (level >= Info)
            log_(msg.toString());
    }

    public void info(String format, String... args)
    {
        if (level >= Info)
            log_(String.format(format, args));
    }

    private void log_(String msg)
    {
        System.err.println("AUDIT:" + msg); // FIXME
    }
}

