// Source File Name:   Dsasignver.java

import java.math.BigInteger;

public class Dsasignver
{

    public Dsasignver()
    {
    }

    public String doit(String s)
        throws Exception
    {
        boolean flag2 = true;
        boolean flag1;
        boolean flag = flag1 = false;
        String s1 = new String();
        String s2 = new String();
        Dsastore dsastore = new Dsastore();
        BigInteger abiginteger[] = dsastore.readout();
        BigInteger biginteger = abiginteger[0];
        BigInteger biginteger1 = abiginteger[1];
        BigInteger biginteger2 = abiginteger[2];
        BigInteger biginteger3 = abiginteger[3];
        for(int i = s.length() - 1; flag2; i--)
        {
            char c = s.charAt(i);
            if(c == 's')
            {
                flag1 = true;
                flag = false;
            } else
            if(c == 'r')
            {
                flag = true;
                flag1 = false;
            } else
            if(c == 'x')
                flag2 = false;
            if(flag1)
                s2 = c + s2;
            else
            if(flag)
                s1 = c + s1;
        }

        int j = s1.length() + s2.length();
        s = s.substring(0, s.length() - j);
        s1 = s1.substring(1, s1.length() - 1);
        s2 = s2.substring(0, s2.length() - 1);
        BigInteger biginteger4 = new BigInteger(s1, 2);
        BigInteger biginteger5 = new BigInteger(s2, 2);
        BigInteger biginteger6 = biginteger5.modInverse(biginteger1);
        BigInteger biginteger7 = biginteger6.mod(biginteger1);
        Dsasha dsasha = new Dsasha();
        BigInteger biginteger8 = dsasha.doit(s);
        BigInteger biginteger9 = biginteger8.multiply(biginteger7).mod(biginteger1);
        BigInteger biginteger10 = biginteger4.multiply(biginteger7).mod(biginteger1);
        BigInteger biginteger11 = biginteger2.modPow(biginteger9, biginteger).multiply(biginteger3.modPow(biginteger10, biginteger)).mod(biginteger).mod(biginteger1);
        if(biginteger11.equals(biginteger4))
            return s;
        else
            return "failure";
    }
}