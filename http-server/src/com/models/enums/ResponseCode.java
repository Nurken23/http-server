package com.models.enums;

public enum ResponseCode
{
    OK(200),
    NOT_FOUND(404);

    private final Integer code;

    ResponseCode(Integer code)
    {
        this.code = code;
    }

    public Integer getCode()
    {
        return code;
    }
}
