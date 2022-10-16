package com.logger.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class FileUtils {

    public static void compressFile(final File source, final File destination) throws IOException {
        final FileInputStream fis = new FileInputStream(source);
        final GZIPOutputStream outputStream = new GZIPOutputStream(new FileOutputStream(destination));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }

        fis.close();
        outputStream.close();
    }
}
