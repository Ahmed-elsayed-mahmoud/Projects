import java.math.BigInteger;

public class Dsasha
{

    public Dsasha()
    {
    }

    public BigInteger doit(String s)
    {
        String s2 = new String();
        int i = s.length();
        int j = i / 250;
        String as[] = new String[j];
        int k = 0;
        Dsamsgdig dsamsgdig = new Dsamsgdig();
        for(; i >= 512; i -= 512)
        {
            s2 = s.substring(0, 512);
            as[k] = dsamsgdig.doit(s2);
            k++;
            s = s.substring(512);
        }

        if(s.length() != 0)
        {
            for(s = s + "1"; s.length() < 448; s = s + "0");
            String s1;
            for(s1 = Integer.toBinaryString(i); s1.length() < 64; s1 = "0" + s1);
            s = s + s1;
            s2 = s;
        }
        as[k] = dsamsgdig.doit(s2);
        k++;
        Dsamethod dsamethod = new Dsamethod();
        String s3 = new String(as[0]);
        for(int l = 1; l < k; l++)
            s3 = dsamethod.exor(s3, as[l]);

        BigInteger biginteger = new BigInteger(s3, 2);
        return biginteger;
    }
}