// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;


// Referenced classes of package com.Saw.framework:
//            Audio, Screen, FileIO, Graphics, 
//            Input

public interface Game
{

    public abstract Audio getAudio();

    public abstract Screen getCurrentScreen();

    public abstract FileIO getFileIO();

    public abstract Graphics getGraphics();

    public abstract Screen getInitScreen();

    public abstract Input getInput();

    public abstract void setScreen(Screen screen);
}
