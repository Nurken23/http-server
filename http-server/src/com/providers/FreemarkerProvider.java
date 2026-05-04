package com.providers;

import static com.utils.EnvironmentConstants.TEMPLATES_DIR;

import com.utils.LogEngine;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;

public class FreemarkerProvider
{
    public static Configuration provide()
    {
        try
        {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_34);

            File file = new File(TEMPLATES_DIR);
            configuration.setDirectoryForTemplateLoading(file);

            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
            configuration.setFallbackOnNullLoopVariable(false);

            return configuration;
        }
        catch (IOException exception)
        {
            LogEngine.error(exception.getMessage());
            throw new RuntimeException();
        }
    }
}
