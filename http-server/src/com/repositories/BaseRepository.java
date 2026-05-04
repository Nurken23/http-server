package com.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utils.FileUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Базовый абстрактный репозиторий для работы с сущностями,
 * сохраняемыми в JSON-файл.
 *
 * <p>Предоставляет:
 * <ul>
 *     <li>Запись списка сущностей в файл</li>
 *     <li>Вспомогательный метод для чтения JSON и десериализации</li>
 * </ul>
 *
 * <p>Классы-наследники должны реализовать метод {@link #read()},
 * используя {@link #readInternal(Class)} для получения данных.
 *
 * @param <T> тип сущности
 */
public abstract class BaseRepository<T>
{
    private final String path;
    private final Gson gson;

    /**
     * Создаёт репозиторий с указанным путём к файлу.
     *
     * @param path путь к JSON-файлу
     */
    protected BaseRepository(String path)
    {
        this.path = path;
        this.gson = new GsonBuilder()
                                .setPrettyPrinting()
                                .create();
    }

    /**
     * Читает и возвращает список сущностей.
     *
     * <p>Должен быть реализован в наследниках.
     *
     * @return список сущностей
     */
    public abstract List<T> read();

    /**
     * Записывает список сущностей в файл в формате JSON.
     *
     * @param entities список сущностей для сохранения
     */
    public void write(List<T> entities)
    {
        try
        {
            String json = gson.toJson(entities);
            FileUtils.write(path, json, false);
        }
        catch (Exception exception)
        {
            // ignore
        }
    }

    /**
     * Вспомогательный метод для чтения данных из файла
     * и десериализации JSON в список объектов.
     *
     * <p>Используется в реализациях {@link #read()}.
     *
     * @param type класс массива сущностей (например, {@code MyEntity[].class})
     * @return список десериализованных сущностей или пустой список при ошибке
     */
    protected List<T> readInternal(Class<T[]> type)
    {
        try
        {
            String json = FileUtils.read(path);
            T[] categories = gson.fromJson(json, type);

            return new ArrayList<>(Arrays.asList(categories));
        }
        catch (Exception exception)
        {
            return new ArrayList<>();
        }
    }
}
