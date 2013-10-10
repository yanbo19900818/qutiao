package com.qutiao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class UncompressStreamGZIP {
    /**
     * 解压gzip
     * @param inputStream
     * @return
     */
    public static String doUncompressFile(InputStream inputStream) {
        try {
            GZIPInputStream in = null;
            in = new GZIPInputStream(inputStream);
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            // System.exit(1); 
        }
        return null;
    }

}