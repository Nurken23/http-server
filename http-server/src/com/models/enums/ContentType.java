package com.models.enums;

public enum ContentType
{
    TEXT_CSS("text/css"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    TEXT_HTML("text/html; charset=utf-8"),
    TEXT_PLAIN("text/plain; charset=utf-8");

    private final String description;

    ContentType(String description)
    {
        this.description =  description;
    }

    @Override
    public String toString()
    {
        return description;
    }
}
