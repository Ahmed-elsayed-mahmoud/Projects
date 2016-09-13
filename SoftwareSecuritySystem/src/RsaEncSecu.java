
public class RsaEncSecu
{

    public RsaEncSecu()
    {
    }

    public String doit(String s, String s1, String s2)
        throws Exception
    {
        String s3 = new String();
        String s4 = new String();
        RsaEncConf rsaencconf = new RsaEncConf();
        RsaEncSign rsaencsign = new RsaEncSign();
        s3 = rsaencconf.doit(s1, s2);
        s4 = rsaencsign.doit(s, s3);
        return s4;
    }
}