// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import com.Saw.framework.Audio;
import com.Saw.framework.Image;
import com.Saw.framework.Music;
import com.Saw.framework.Sound;

// Referenced classes of package com.Saw.game:
//            SampleGame

public class Assets
{

    public static Image background;
    public static Image button;
    public static Image character;
    public static Image character2;
    public static Image character3;
    public static Image characterDown;
    public static Image characterJump;
    public static Sound click;
    public static Music explosion;
    public static Image heliboy;
    public static Image heliboy2;
    public static Image heliboy3;
    public static Image heliboy4;
    public static Image heliboy5;
    public static Image menu;
    public static Image splash;
    public static Music theme;
    public static Image tiledirt;
    public static Image tilegrassBot;
    public static Image tilegrassLeft;
    public static Image tilegrassRight;
    public static Image tilegrassTop;

    public Assets()
    {
    }

    public static void load(SampleGame samplegame)
    {
        theme = samplegame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.95F);
        theme.play();
    }
}
