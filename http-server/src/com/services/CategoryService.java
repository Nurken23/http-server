package com.services;

import com.entities.Category;
import com.models.filters.CategoryFilter;
import com.models.requests.CreateCategoryRequest;
import com.models.requests.UpdateCategoryRequest;
import com.repositories.impl.CategoryRepository;
import com.utils.LogEngine;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryService
{
    private final CategoryRepository repository;
    private final List<Category> categories;

    public CategoryService()
    {
        this.repository = new CategoryRepository();
        this.categories = repository.read();
    }

    public void create(CreateCategoryRequest Request)
    {
        if (Request == null)
        {
            LogEngine.info("Request for updating category is null, skip");
            return;
        }

        LogEngine.info("Request for creating category, body (" + Request + ")");

        String title = Request.title();
        Category parent = Request.parent();

        Category category = new Category(title, parent);
        categories.add(category);

        repository.write(categories);
    }

    public List<Category> getAll()
    {
        return getAll(null);
    }

    public List<Category> getAll(CategoryFilter filter)
    {
        LogEngine.info("Request for getting categories by filters (" + filter + ")");

        if (filter == null)
        {
            return categories;
        }

        Stream<Category> stream = categories.stream();

        String title = filter.title();
        if (title != null && !title.isBlank())
        {
            stream = stream.filter(it -> it.getTitle().equalsIgnoreCase(title));
        }

        String parent = filter.parent();
        if (parent != null && !parent.isBlank())
        {
            stream = stream
                    .filter(it -> !it.isRoot())
                    .filter(it -> it.getParent().getTitle().equalsIgnoreCase(parent));
        }

        return stream.collect(Collectors.toList());
    }

    public List<Category> getChildren(Category category)
    {
        UUID id = category.getId();

        LogEngine.info("Request for getting children of category with id: " + id);

        return categories
                .stream()
                .filter(it -> !it.isRoot())
                .filter(it -> it.getParent().getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Category> getParents()
    {
        LogEngine.info("Request for getting parent categories");

        return categories
                .stream()
                .filter(Category::isRoot)
                .collect(Collectors.toList());
    }

    public Category getById(UUID id)
    {
        LogEngine.info("Request for getting category with id: " + id);

        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("category with id (" + id + ") not found"));
    }

    public void update(UpdateCategoryRequest Request)
    {
        if (Request == null)
        {
            LogEngine.info("Request for updating category is null, skip");
            return;
        }

        UUID id = Request.id();
        LogEngine.info("Request for updating category with id: " + id + ", body (" + Request + ")");
        Category category = getById(id);

        String title = Request.title();
        category.setTitle(title);

        Category parent = Request.parent();
        category.setParent(parent);

        repository.write(categories);
    }

    public void delete(UUID id)
    {
        LogEngine.info("Request for deleting category with id: " + id);

        Category category = getById(id);

        categories.remove(category);
        repository.write(categories);
    }

    private Optional<Category> findById(UUID id)
    {
        for (Category category : categories)
        {
            if (id.equals(category.getId()))
            {
                return Optional.of(category);
            }
        }

        return Optional.empty();
    }
}
