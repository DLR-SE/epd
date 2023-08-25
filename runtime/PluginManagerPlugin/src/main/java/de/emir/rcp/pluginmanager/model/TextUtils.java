package de.emir.rcp.pluginmanager.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextUtils {

    /**
     * https://stackoverflow.com/questions/20039980/java-replace-line-in-text-file
     *
     * @param path
     * @param startString
     * @param value
     * @return
     * @throws Exception
     */
    public static String replaceText(Path path, String startString, String value) throws Exception {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        return replaceText(fileContent, startString, value);
    }

    public static String replaceText(String str, String startString, String value) {
        return replaceText(new ArrayList<>(Arrays.asList(str.split("\n"))), startString, value);
    }

    public static String replaceText(List<String> strings, String startString, String value) {
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).trim().startsWith(startString)) {
                strings.set(i, value);
                break;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str + "\n");
        }

        return builder.toString();
    }

    public static String getFileContent(InputStream stream) {
        List<String> content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line = null;

            content = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        for (String str : content) {
            builder.append(str + "\n");
        }

        return builder.toString();
    }

    public static String replaceSpecialChars(String string) {
        return string.replaceAll("[\\s+\\-\\(\\)]", "").replaceAll("\\.", "");
    }

}
