package com.utils;

import com.sun.net.httpserver.HttpExchange;

public class EndpointPathBuilder
{
    public static String build(HttpExchange exchange)
    {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        int index = path.lastIndexOf(".");
        if (index != -1)
        {
            return build(method, path.substring(index).toLowerCase());
        }

        return build(method, path);
    }

    private static String build(String method, String path)
    {
        return String.format("%s %s", method.toUpperCase(), path);
    }
}
