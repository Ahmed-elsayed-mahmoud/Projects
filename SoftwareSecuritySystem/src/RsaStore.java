// Source File Name:   Rsastore.java


public class RsaStore
{

    public RsaStore()
    {
    }

    public String getstring(int i, int j, int k)
        throws Exception
    {
        String s = new String();
        String s1 = new String();
        String s2 = new String();
        String _tmp = s;
        s = String.valueOf(i);
        String _tmp1 = s1;
        s1 = String.valueOf(j);
        String _tmp2 = s2;
        s2 = String.valueOf(k);
        String s3 = new String();
        for(; s.length() < 10; s = "0" + s);
        for(; s1.length() < 10; s1 = "0" + s1);
        for(; s2.length() < 10; s2 = "0" + s2);
        s3 = s + s1 + s2;
        return s3;
    }

    public int[] getinteger(String s)
        throws Exception
    {
        String s1 = new String();
        String s2 = new String();
        String s3 = new String();
        s1 = s.substring(0, 10);
        s2 = s.substring(10, 20);
        s3 = s.substring(20, 30);
        int i = Integer.parseInt(s1);
        int j = Integer.parseInt(s2);
        int k = Integer.parseInt(s3);
        int ai[] = new int[3];
        ai[0] = i;
        ai[1] = j;
        ai[2] = k;
        return ai;
    }
}