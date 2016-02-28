// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;


// Referenced classes of package com.Saw.framework:
//            Game

public abstract class Screen
{

    protected final Game game;

    public Screen(Game game1)
    {
        game = game1;
    }

    public abstract void backButton();

    public abstract void dispose();

    public abstract void paint(float f);

    public abstract void pause();

    public abstract void resume();

    public abstract void update(float f);
}
