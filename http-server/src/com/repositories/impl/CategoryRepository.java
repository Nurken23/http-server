package com.repositories.impl;

import com.entities.Category;
import com.repositories.BaseRepository;
import com.utils.EnvironmentConstants;
import java.util.List;

public class CategoryRepository extends BaseRepository<Category>
{
    public CategoryRepository()
    {
        super(EnvironmentConstants.CATEGORIES_DB);
    }

    @Override
    public List<Category> read()
    {
        return readInternal(Category[].class);
    }
}
