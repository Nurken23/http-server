package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static String read(String path) throws IOException
    {
        FileReader base = new FileReader(path);
        BufferedReader reader = new BufferedReader(base);

        String line;

        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder
                    .append(line)
                    .append("\n");
        }

        reader.close();
        return builder.toString();
    }

    public static void write(String path, String value, boolean append) throws IOException
    {
        FileWriter base = new FileWriter(path, append);
        BufferedWriter writer = new BufferedWriter(base);

        writer.write(value);

        writer.close();
    }
}
