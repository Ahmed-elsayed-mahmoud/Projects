// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;


// Referenced classes of package com.Saw.game:
//            GameScreen

static final class  extends Enum
{

    private static final GameOver ENUM$VALUES[];
    public static final GameOver GameOver;
    public static final GameOver Paused;
    public static final GameOver Ready;
    public static final GameOver Running;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/Saw/game/GameScreen$GameState, s);
    }

    public static [] values()
    {
         a[] = ENUM$VALUES;
        int i = a.length;
         a1[] = new ENUM.VALUES[i];
        System.arraycopy(a, 0, a1, 0, i);
        return a1;
    }

    static 
    {
        Ready = new <init>("Ready", 0);
        Running = new <init>("Running", 1);
        Paused = new <init>("Paused", 2);
        GameOver = new <init>("GameOver", 3);
        ENUM$VALUES = (new ENUM.VALUES[] {
            Ready, Running, Paused, GameOver
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
