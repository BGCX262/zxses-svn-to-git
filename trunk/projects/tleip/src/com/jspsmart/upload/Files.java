package com.jspsmart.upload;

import java.io.IOException;
import java.util.*;

// Referenced classes of package com.jspsmart.upload:
//            File, SmartUpload

public class Files
{

    @SuppressWarnings("unused")
	private SmartUpload m_parent;
    private Hashtable<Integer, File> m_files;
    private int m_counter;

    Files()
    {
        m_files = new Hashtable<Integer, File>();
        m_counter = 0;
    }

    protected void addFile(File newFile)
    {
        if(newFile == null)
        {
            throw new IllegalArgumentException("newFile cannot be null.");
        } else
        {
            m_files.put(new Integer(m_counter), newFile);
            m_counter++;
            return;
        }
    }

    public File getFile(int index)
    {
        if(index < 0)
            throw new IllegalArgumentException("File's index cannot be a negative value (1210).");
        File retval = (File)m_files.get(new Integer(index));
        if(retval == null)
            throw new IllegalArgumentException("Files' name is invalid or does not exist (1205).");
        else
            return retval;
    }

    public int getCount()
    {
        return m_counter;
    }

    public long getSize()
        throws IOException
    {
        long tmp = 0L;
        for(int i = 0; i < m_counter; i++)
            tmp += getFile(i).getSize();

        return tmp;
    }

    public Collection<File> getCollection()
    {
        return m_files.values();
    }

    public Enumeration<File> getEnumeration()
    {
        return m_files.elements();
    }
}
