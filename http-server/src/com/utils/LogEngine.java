package com.utils;

import static com.utils.EnvironmentConstants.LOG_FILE;

import java.time.LocalDateTime;

public class LogEngine
{
    private static final String TEMPLATE = "{\"level\": \"%s\"; \"timestamp\": \"%-26s\"; \"message\": \"%s\"}";

    public static void error(String message)
    {
        message = TEMPLATE.formatted("ERROR", LocalDateTime.now(), message);
        System.out.println(message);

        write(message);
    }

    public static void info(String message)
    {
        message = TEMPLATE.formatted("INFO", LocalDateTime.now(), message);
        System.out.println(message);

        write(message);
    }

    private static void write(String message)
    {
        try
        {
            FileUtils.write(LOG_FILE, message + "\n", true);
        }
        catch (Exception exception)
        {
            // ignore
        }
    }
}
