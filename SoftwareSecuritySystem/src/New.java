import java.io.*;

public class New
{

    public New()
    {
    }

    public String Newalg(String s)
    {
        String s3 = "";
        char ac[] = new char[8];
        char ac1[] = new char[8];
        String s4 = new String();
        try
        {
            FileWriter filewriter = new FileWriter("plainnew.dat");
            filewriter.write(s);
            filewriter.close();
            FileReader filereader = new FileReader("plainnew.dat");
            int i;
            while((i = filereader.read()) != -1) 
            {
                String s1;
                for(s1 = Integer.toBinaryString(i); s1.length() < 8; s1 = "0" + s1);
                s3 = s3 + s1;
            }
        }
        catch(IOException ioexception)
        {
            System.out.println(ioexception);
        }
        for(int j = 0; j < s3.length(); j += 8)
        {
            String s2 = s3.substring(j, j + 8);
            s2.getChars(0, 8, ac, 0);
            ac1[0] = ac[0];
            ac1[1] = ac[4];
            ac1[2] = ac[1];
            ac1[3] = ac[5];
            ac1[4] = ac[2];
            ac1[5] = ac[6];
            ac1[6] = ac[3];
            ac1[7] = ac[7];
            for(int k = 0; k < 8; k++)
                s4 = s4 + ac1[k];

        }

        return s4;
    }
}