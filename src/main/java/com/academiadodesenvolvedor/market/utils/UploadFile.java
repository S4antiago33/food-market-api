package com.academiadodesenvolvedor.market.utils;

import org.apache.tika.Tika;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class UploadFile {

    public static String storeFile(String base64Image) throws IOException {
        String name = UUID.randomUUID().toString();
        return storeFile(base64Image, new String[] {"files"}, name);
    }

    public static String storeFile(String base64Image, String[] path) throws IOException {
        String name = UUID.randomUUID().toString();
        return storeFile(base64Image, path, name);
    }
    public static String storeFile(String base64Image, String[] path, String name) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String mimeType = getMimeType(imageBytes);
        String extensionFile = mimeType.split("/")[1];
        String fileName = name + "." + extensionFile;
        Path storage = Paths.get("storage", path);
        Path file = storage.resolve(fileName);

        Files.createDirectories(storage);

        try(FileOutputStream stream = new FileOutputStream(file.toFile())) {
            stream.write(imageBytes);
        }

        return fileName;
    }
    public static String getMimeType(byte[] file) throws IOException{
        Tika tika = new Tika();
        return tika.detect(file);
    }
}
