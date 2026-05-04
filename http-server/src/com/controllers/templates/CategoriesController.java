package com.controllers.templates;

import com.controllers.base.TemplateController;
import com.entities.Category;
import com.services.CategoryService;
import java.util.List;
import java.util.Map;

public class CategoriesController extends TemplateController
{
    private final CategoryService service = new CategoryService();

    @Override
    public byte[] process() throws Exception
    {
        List<Category> categories = service.getAll();

        Map<String, List<Category>> data = Map.of("categories", categories);
        return process(data, "categories.ftl");
    }
}
