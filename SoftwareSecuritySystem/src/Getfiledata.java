// Source File Name:   Sss.java

import java.io.FileReader;
import java.io.IOException;

class Getfiledata
{

    Getfiledata()
    {
    }

    public String doit(String s)
    {
        String s1 = "";
        try
        {
            FileReader filereader = new FileReader(s);
            int i;
            while((i = filereader.read()) != -1) 
                s1 = s1 + (char)i;
            filereader.close();
        }
        catch(IOException ioexception) { }
        return s1;
    }
}