package com.providers;

import com.controllers.base.Controller;
import com.controllers.base.FileController;
import com.controllers.errors.NotFoundController;
import com.controllers.templates.CategoriesController;
import com.models.enums.ContentType;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.utils.EndpointPathBuilder;
import java.util.HashMap;
import java.util.Map;

// 1. Инициализируем поле controllers (key = endpoint, value = controller),
// 2. Регистрируем http context - '/', то есть любой запрос всегда сначала попадёт в метод find,
// 3. В методе find по URI запроса мы достаём нужный обработчик, если URI не найден - отдаём дефолтный (NotFoundController),
// 4. Конкретный обработчик далее обрабатывает и отдаёт ответ.

public class HttpContextProvider
{
    private final Map<String, Controller> controllers = new HashMap<>();

    public HttpContextProvider()
    {
        build();
    }

    public void provide(HttpServer server)
    {
        server.createContext("/", this::find);
    }

    private void find(HttpExchange exchange)
    {
        String path = EndpointPathBuilder.build(exchange);

        Controller controller = controllers.getOrDefault(path, new NotFoundController());
        controller.handle(exchange);
    }

    private void build()
    {
        controllers.put("GET /categories", new CategoriesController());

        controllers.put("GET .css", new FileController(ContentType.TEXT_CSS));
        controllers.put("GET .gif", new FileController(ContentType.IMAGE_GIF));
        controllers.put("GET .png", new FileController(ContentType.IMAGE_PNG));
        controllers.put("GET .jpg", new FileController(ContentType.IMAGE_JPEG));
        controllers.put("GET .jpeg", new FileController(ContentType.IMAGE_JPEG));
    }
}
