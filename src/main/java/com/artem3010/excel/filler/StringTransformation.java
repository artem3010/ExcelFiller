package com.artem3010.excel.filler;

import java.util.*;

public class StringTransformation {
    private static Map<String, String> mapEngRuLiterals = new HashMap<>();

    static {
        mapEngRuLiterals.put("A", "А");
        mapEngRuLiterals.put("SB", "СБ");
        mapEngRuLiterals.put("U", "У");
        mapEngRuLiterals.put("ZH", "Ж");
        mapEngRuLiterals.put("RTO", "РТО");
    }

    public static String transform(String fileName) {
       fileName = fileName.toUpperCase(Locale.ROOT);
        if (fileName.contains("-")) {
            //TODO: method for determine whether you need to replace '-' with ':'
        }
        fileName = fileName.replaceAll("_.*[^.pdf]", "");
        fileName = fileName.replace("=", ":");
        replacerEngToRu(fileName);

        return fileName;
    }

    private static String replacerEngToRu(String string) {

        for (String key : mapEngRuLiterals.keySet()
        ) {
            string = string.replace(key, mapEngRuLiterals.get(key));
        }
        return string;
    }
}
