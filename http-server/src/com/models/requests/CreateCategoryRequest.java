package com.models.requests;

import com.entities.Category;

public record CreateCategoryRequest
(
        String title,
        Category parent
) { }
