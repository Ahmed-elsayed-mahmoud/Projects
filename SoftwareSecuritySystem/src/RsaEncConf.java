import java.io.*;
import java.math.BigInteger;

public class RsaEncConf
{

    public RsaEncConf()
    {
    }

    public String doit(String s, String s1)
        throws Exception
    {
        String s2 = new String();
        String s3 = new String();
        int ai[] = new int[3];
        Rsa rsa = new Rsa();
        ai = rsa.doit(s);
        int i = ai[0];
        int j = ai[1];
        int k = ai[2];
        boolean flag = false;
        try
        {
            FileWriter filewriter = new FileWriter("plainrsa.dat");
            filewriter.write(s1);
            filewriter.close();
            FileReader filereader = new FileReader("plainrsa.dat");
            long l1;
            while((l1 = filereader.read()) != -1L) 
            {
                BigInteger biginteger = BigInteger.valueOf(l1);
                BigInteger biginteger1 = BigInteger.valueOf(k);
                BigInteger biginteger2 = biginteger.pow(j).mod(biginteger1);
                long l = biginteger2.longValue();
                String _tmp = s3;
                for(s3 = String.valueOf(l); s3.length() < 8; s3 = "0" + s3);
                s2 = s2 + s3;
            }
            filereader.close();
        }
        catch(IOException ioexception)
        {
            System.out.println(ioexception);
        }
        RsaStore rsastore = new RsaStore();
        String s4 = rsastore.getstring(i, j, k);
        s2 = s2 + s4;
        return s2;
    }
}