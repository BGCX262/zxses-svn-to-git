package com.zx.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class FileUtil {
    static Logger logger = Logger.getLogger(FileUtil.class.getName());

    private static FileUtil instance = null;

    public static synchronized FileUtil getInstance() {
        if (instance == null)
            instance = new FileUtil();
        return instance;
    }

    public String getExtension(File f) {
        return (f != null) ? getExtension(f.getName()) : "";
    }

    public String getExtension(String filename) {
        String defExt = "";
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > -1) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return defExt;
    }

    public String trimExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > -1) && (i < (filename.length()))) {
                return filename.substring(0, i);
            }
        }
        return filename;
    }

    public static boolean copy(String oldPath, String newPath) {
        boolean status = false;
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                status = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return status;
    }

}
