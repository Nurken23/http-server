package com.controllers.base;

import static com.models.enums.ResponseCode.OK;

import com.providers.FreemarkerProvider;
import com.sun.net.httpserver.HttpExchange;
import com.utils.LogEngine;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

// Так-как у нас отличается только процесс записи контента (HTML)
// мы сделали абстрактный класс BaseController, где логика обработки
// всегда одинаковая, а процесс записи контента разный.

// Разный он из-за полиморфизма метода process.
public abstract class TemplateController implements Controller
{
    protected final Configuration configuration = FreemarkerProvider.provide();

    @Override
    public void handle(HttpExchange exchange)
    {
        try
        {
            LogEngine.info("Request: " + exchange.getRequestMethod() + ", " + exchange.getRequestURI() + ", "
                    + exchange.getRemoteAddress());

            exchange
                    .getResponseHeaders()
                    .add("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(OK.getCode(), 0);

            OutputStream output = exchange.getResponseBody();

            byte[] bytes = process();

            output.write(bytes);
            output.flush();
            output.close();
        }
        catch (Exception exception)
        {
            LogEngine.error(exception.getMessage());
        }
    }

    public byte[] process(Object data, String path) throws Exception
    {
        Template template = configuration.getTemplate(path);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(stream);

        template.process(data, writer);
        writer.flush();
        writer.close();

        return stream.toByteArray();
    }

    public abstract byte[] process() throws Exception;
}
