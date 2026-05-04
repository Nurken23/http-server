package com.entities;

import java.util.UUID;

public class Category
{
    private final UUID id;

    private String title;
    private Category parent;

    public Category(String title, Category parent)
    {
        this.id = UUID.randomUUID();

        this.title = title;
        this.parent = parent;
    }

    public boolean isRoot()
    {
        return parent == null;
    }

    public UUID getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public Category getParent()
    {
        return parent;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setParent(Category parent)
    {
        this.parent = parent;
    }
}
