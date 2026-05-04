package com.controllers.base;

import static com.models.enums.ResponseCode.OK;
import static com.utils.EnvironmentConstants.TEMPLATES_DIR;

import com.models.enums.ContentType;
import com.sun.net.httpserver.HttpExchange;
import com.utils.LogEngine;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

// 1. Пользователь зашёл на сайте
// 2. Браузер (клиент) отправляет запрос на сервер на получение данных по адресу /catalog/123
// 3. Клиент получает страницу, на странице картина
// 4. Клиент делает следующий запрос на получение картинки
// 5. Клиент рисует картинку

// /images/test.gif -> /resources/templates/images/test.gif -> 0011010110110110111

public class FileController implements Controller
{
    private final ContentType type;

    public FileController(ContentType type)
    {
        this.type = type;
    }

    @Override
    public void handle(HttpExchange exchange)
    {
        try
        {
            LogEngine.info("Request: " + exchange.getRequestMethod() + ", " + exchange.getRequestURI() + ", " + exchange.getRemoteAddress());

            exchange
                    .getResponseHeaders()
                    .add("Content-Type", type.toString());
            exchange.sendResponseHeaders(OK.getCode(), 0);

            process(exchange);
        }
        catch (IOException exception)
        {
            LogEngine.error(exception.getMessage());
        }
    }

    private void process(HttpExchange exchange) throws IOException
    {
        OutputStream output = exchange.getResponseBody();

        URI uri = exchange.getRequestURI();
        Path path = Path.of(TEMPLATES_DIR, uri.getPath());

        byte[] bytes = Files.readAllBytes(path);

        output.write(bytes);
        output.flush();
        output.close();
    }
}
