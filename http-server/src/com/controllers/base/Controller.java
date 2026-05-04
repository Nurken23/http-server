package com.controllers.base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// Виды контроллеров:
// 1. Отдаёт в ответе FTL шаблон,
// 2. Отдаёт файлы с расширением: .css, .jpg, .jpeg, .png, .gif,
// 3. Что-то принимает из тела запроса, но возвращаем просто статус код (создания, удаления, обновления).

public interface Controller extends HttpHandler
{
    void handle(HttpExchange exchange);
}
