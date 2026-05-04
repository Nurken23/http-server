import static com.utils.EnvironmentConstants.HOST;
import static com.utils.EnvironmentConstants.PORT;

import com.providers.HttpServerProvider;
import com.providers.HttpContextProvider;
import com.sun.net.httpserver.HttpServer;
import com.utils.LogEngine;

// Сервер пока-что умеет работать только с GET запросами. Картинки отдавать пока не умеет.

// 0. Делаем HTML,
// 1. Делаем имплементацию от BaseController,
// 2. Добавляем endpoint + имплементацию BaseController в HttpContextProvider,

public class Application
{
    public static void main(String... args)
    {
        try
        {
            HttpServer server = HttpServerProvider.provide(HOST, PORT);

            HttpContextProvider provider = new HttpContextProvider();
            provider.provide(server);

            server.start();
        }
        catch (Exception exception)
        {
            LogEngine.error(exception.getMessage());
        }
    }
}
