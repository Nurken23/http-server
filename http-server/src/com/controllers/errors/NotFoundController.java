package com.controllers.errors;

import com.controllers.base.TemplateController;

public class NotFoundController extends TemplateController
{
    @Override
    public byte[] process() throws Exception
    {
        return process(null, "404.ftl");
    }
}
