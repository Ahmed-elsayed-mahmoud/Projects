
public class SBox1
{

    public SBox1()
    {
        Sub = "";
    }

    public void Sbox1(int i, String as[], int ai[][])
    {
        int l = 0;
        int i1 = 0;
        int ai1[] = new int[7];
        char ac[] = new char[7];
        as[i].getChars(0, 6, ac, 1);
        String s = "";
        for(int j = 1; j < 7; j++)
            ai1[j] = ac[j] - 48;

        l = ai1[1] * 2 + ai1[6] * 1;
        i1 = ai1[2] * 8 + ai1[3] * 4 + ai1[4] * 2 + ai1[5] * 1;
        int k = ai[l][i1];
        for(s = Integer.toBinaryString(k); s.length() < 4; s = "0" + s);
        Sub += s;
    }

    String Sub;
}