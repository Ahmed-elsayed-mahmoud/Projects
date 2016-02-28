// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import com.Saw.framework.Game;
import com.Saw.framework.Graphics;
import com.Saw.framework.Screen;

// Referenced classes of package com.Saw.game:
//            Assets, MainMenuScreen

public class LoadingScreen extends Screen
{

    public LoadingScreen(Game game)
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
        game.getGraphics().drawImage(Assets.splash, 0, 0);
    }

    public void pause()
    {
    }

    public void resume()
    {
    }

    public void update(float f)
    {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.background = g.newImage("background.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.character = g.newImage("character.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.character2 = g.newImage("character2.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.character3 = g.newImage("character3.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.characterJump = g.newImage("jumped.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.characterDown = g.newImage("down.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.heliboy = g.newImage("heliboy.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.heliboy2 = g.newImage("heliboy2.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.heliboy3 = g.newImage("heliboy3.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.heliboy4 = g.newImage("heliboy4.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.heliboy5 = g.newImage("heliboy5.png", com.Saw.framework.Graphics.ImageFormat.ARGB4444);
        Assets.tiledirt = g.newImage("tiledirt.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.tilegrassTop = g.newImage("tilegrasstop.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.tilegrassBot = g.newImage("tilegrassbot.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.tilegrassLeft = g.newImage("tilegrassleft.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.tilegrassRight = g.newImage("tilegrassright.png", com.Saw.framework.Graphics.ImageFormat.RGB565);
        Assets.button = g.newImage("button.jpg", com.Saw.framework.Graphics.ImageFormat.RGB565);
        game.setScreen(new MainMenuScreen(game));
    }
}
