package com.providers;

import com.sun.net.httpserver.HttpServer;
import com.utils.LogEngine;
import java.io.IOException;
import java.net.InetSocketAddress;

// Provider - то что, что-то предоставляет в данном случае HttpServer.
public class HttpServerProvider
{
    public static HttpServer provide(String host, Integer port) throws IOException
    {
        InetSocketAddress address = new InetSocketAddress(host, port);
        LogEngine.info("Server started: http://" + host + ":" + port);

        return HttpServer.create(address, 50);
    }
}
