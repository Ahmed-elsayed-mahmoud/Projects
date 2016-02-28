// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import com.Saw.framework.Game;
import com.Saw.framework.Graphics;
import com.Saw.framework.Screen;

// Referenced classes of package com.Saw.game:
//            Assets, LoadingScreen

public class SplashLoadingScreen extends Screen
{

    public SplashLoadingScreen(Game game)
    {
        super(game);
    }

    public void backButton()
    {
    }

    public void dispose()
    {
    }

    public void paint(float f)
    {
    }

    public void pause()
    {
    }

    public void resume()
    {
    }

    public void update(float f)
    {
        Assets.splash = game.getGraphics().newImage("splash.jpg", com.Saw.framework.Graphics.ImageFormat.RGB565);
        game.setScreen(new LoadingScreen(game));
    }
}
