import java.math.BigInteger;

public class Dsasigngen
{

    public Dsasigngen()
    {
    }

    public String doit(String s)
        throws Exception
    {
        BigInteger abiginteger[] = new BigInteger[2];
        BigInteger abiginteger1[] = new BigInteger[4];
        Dsa dsa = new Dsa();
        abiginteger = dsa.doit();
        BigInteger biginteger = abiginteger[0];
        BigInteger biginteger1 = abiginteger[1];
        Dsastore dsastore = new Dsastore();
        abiginteger1 = dsastore.readout();
        BigInteger biginteger2 = abiginteger1[0];
        BigInteger biginteger3 = abiginteger1[1];
        BigInteger biginteger4 = abiginteger1[2];
        BigInteger biginteger5 = abiginteger1[3];
        BigInteger biginteger6 = biginteger4.modPow(biginteger1, biginteger2).mod(biginteger3);
        Dsasha dsasha = new Dsasha();
        BigInteger biginteger7 = dsasha.doit(s);
        BigInteger biginteger8 = biginteger1.modInverse(biginteger3);
        BigInteger biginteger9 = biginteger8.multiply(biginteger7.add(biginteger.multiply(biginteger6))).mod(biginteger3);
        BigInteger abiginteger2[] = new BigInteger[2];
        abiginteger2[0] = biginteger6;
        abiginteger2[1] = biginteger9;
        String s1 = biginteger6.toString(2);
        String s2 = biginteger9.toString(2);
        String s3 = new String();
        s3 = s + "x" + s1 + "r" + s2 + "s";
        return s3;
    }
}