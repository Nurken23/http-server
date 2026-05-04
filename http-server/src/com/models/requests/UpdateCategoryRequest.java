package com.models.requests;

import com.entities.Category;
import java.util.UUID;

public record UpdateCategoryRequest
(
        UUID id,
        String title,
        Category parent
) { }
