package com.lcweb.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


public class FileUpLoad {
	private static String newline = "\n";
    private String uploadDirectory;
    private String ContentType;
    private String CharacterEncoding;
    public String FileName;
    public String filetimename;

    public FileUpLoad()
    {
        uploadDirectory = ".";
        ContentType = "";
        CharacterEncoding = "";
    }

    public String GetFileName()
    {
        return FileName;
    }

    public String Getfiletimename()
    {
        return filetimename;
    }

    private String getFileName(String s)
    {
        int i = s.lastIndexOf("\\");
        if(i < 0 || i >= s.length() - 1)
        {
            i = s.lastIndexOf("/");
            if(i < 0 || i >= s.length() - 1)
                return s;
        }
        FileName = s.substring(i + 1);
        return s.substring(i + 1);
    }

    private String readLine(byte abyte0[], int ai[], ServletInputStream servletinputstream, String s)
    {
        try
        {
            ai[0] = servletinputstream.readLine(abyte0, 0, abyte0.length);
            if(ai[0] == -1)
                return null;
        }
        catch(IOException _ex)
        {
            return null;
        }
        try
        {
            if(s == null)
                return new String(abyte0, 0, ai[0]);
            else
                return new String(abyte0, 0, ai[0], s);
        }
        catch(Exception _ex)
        {
            return null;
        }
    }

    public void setCharacterEncoding(String s)
    {
        CharacterEncoding = s;
    }

    public void setContentType(String s)
    {
        ContentType = s;
        int i;
        if((i = ContentType.indexOf("boundary=")) != -1)
        {
            ContentType = ContentType.substring(i + 9);
            ContentType = "--" + ContentType;
        }
    }

    public void setUploadDirectory(String s)
    {
        uploadDirectory = s;
    }

    public void uploadFile(ServletInputStream servletinputstream)
        throws ServletException, IOException
    {
        String s = null;
        String s1 = null;
        byte abyte0[] = new byte[4096];
        byte abyte1[] = new byte[4096];
        int ai[] = new int[1];
        int ai1[] = new int[1];
        String s2;
        while((s2 = readLine(abyte0, ai, servletinputstream, CharacterEncoding)) != null) 
        {
            int i = s2.indexOf("filename=");
            if(i >= 0)
            {
                s2 = s2.substring(i + 10);
                if((i = s2.indexOf("\"")) > 0)
                    s2 = s2.substring(0, i);
                break;
            }
        }
        s1 = s2;
        if(s1 != null && !s1.equals("\""))
        {
            s1 = getFileName(s1);
            String s3 = Long.toString((new Date()).getTime());
            String extName = s1.substring(s1.indexOf("."), s1.length());
            /*
            if (extName.equalsIgnoreCase(".JPG")){
            	extName = ".gif";
            }
            */
            //filetimename = s3 + s1.substring(s1.indexOf("."), s1.length());
            filetimename = s3 + extName;
            String s4 = readLine(abyte0, ai, servletinputstream, CharacterEncoding);
            if(s4.indexOf("Content-Type") >= 0){
                readLine(abyte0, ai, servletinputstream, CharacterEncoding);
            }
            //SystemConfig.getInstance().reload();
            //uploadDirectory = SystemConfig.getInstance().getString(uploadDirectory);
            File file = new File(uploadDirectory, filetimename);
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            while((s4 = readLine(abyte0, ai, servletinputstream, CharacterEncoding)) != null) 
            {
                if(s4.indexOf(ContentType) == 0 && abyte0[0] == 45)
                    break;
                if(s != null)
                {
                    fileoutputstream.write(abyte1, 0, ai1[0]);
                    fileoutputstream.flush();
                }
                s = readLine(abyte1, ai1, servletinputstream, CharacterEncoding);
                if(s == null || s.indexOf(ContentType) == 0 && abyte1[0] == 45)
                    break;
                fileoutputstream.write(abyte0, 0, ai[0]);
                fileoutputstream.flush();
            }
            byte byte0;
            if(newline.length() == 1)
                byte0 = 2;
            else
                byte0 = 1;
            if(s != null && abyte1[0] != 45 && ai1[0] > newline.length() * byte0)
                fileoutputstream.write(abyte1, 0, ai1[0] - newline.length() * byte0);
            if(s4 != null && abyte0[0] != 45 && ai[0] > newline.length() * byte0)
                fileoutputstream.write(abyte0, 0, ai[0] - newline.length() * byte0);
            fileoutputstream.close();
        }
    }

    public void uploadFile(HttpServletRequest httpservletrequest)
        throws ServletException, IOException
    {
        setCharacterEncoding(httpservletrequest.getCharacterEncoding());
        setContentType(httpservletrequest.getContentType());
        uploadFile(httpservletrequest.getInputStream());
    }

}
