package com.pelican.battle.server.utils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DiskUtils {
    public static String readString(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    public static String readFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
