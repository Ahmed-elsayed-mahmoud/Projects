// Source File Name:   Newdecrypt.java

import java.io.*;

public class Newdecrypt
{

    public Newdecrypt()
    {
    }

    public String Newdecryptalg(String s)
    {
        String s1 = "";
        String s3 = new String();
        char ac[] = new char[8];
        char ac1[] = new char[8];
        String s4 = "";
        try
        {
            FileWriter filewriter = new FileWriter("ciphernew.dat");
            filewriter.write(s);
            filewriter.close();
            FileReader filereader = new FileReader("ciphernew.dat");
            int i;
            while((i = filereader.read()) != -1) 
                s3 = s3 + (char)i;
            filereader.close();
        }
        catch(IOException ioexception) { }
        for(int j = 0; j < s3.length(); j += 8)
        {
            String s2 = s3.substring(j, j + 8);
            s2.getChars(0, 8, ac, 0);
            ac1[0] = ac[0];
            ac1[1] = ac[2];
            ac1[2] = ac[4];
            ac1[3] = ac[6];
            ac1[4] = ac[1];
            ac1[5] = ac[3];
            ac1[6] = ac[5];
            ac1[7] = ac[7];
            for(int k = 0; k < 8; k++)
                s4 = s4 + ac1[k];

        }

        String s5 = "";
        String s7 = "";
        for(int i1 = 0; i1 < s4.length(); i1 += 8)
        {
            String s6 = "";
            s6 = s4.substring(i1, i1 + 8);
            int l = Integer.parseInt(s6, 2);
            s7 = s7 + (char)l;
        }

        return s7;
    }
}