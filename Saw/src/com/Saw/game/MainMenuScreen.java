// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.os.Process;
import com.Saw.framework.Game;
import com.Saw.framework.Graphics;
import com.Saw.framework.Input;
import com.Saw.framework.Screen;
import java.util.List;

// Referenced classes of package com.Saw.game:
//            Assets, GameScreen

public class MainMenuScreen extends Screen
{

    public MainMenuScreen(Game game)
    {
        super(game);
    }

    private boolean inBounds(com.Saw.framework.Input.TouchEvent touchevent, int i, int j, int k, int l)
    {
        return touchevent.x > i && touchevent.x < (i + k) - 1 && touchevent.y > j && touchevent.y < (j + l) - 1;
    }

    public void backButton()
    {
        Process.killProcess(Process.myPid());
    }

    public void dispose()
    {
    }

    public void paint(float f)
    {
        game.getGraphics().drawImage(Assets.menu, 0, 0);
    }

    public void pause()
    {
    }

    public void resume()
    {
    }

    public void update(float f)
    {
        game.getGraphics();
        List list = game.getInput().getTouchEvents();
        int j = list.size();
        int i = 0;
        do
        {
            if (i >= j)
            {
                return;
            }
            com.Saw.framework.Input.TouchEvent touchevent = (com.Saw.framework.Input.TouchEvent)list.get(i);
            if (touchevent.type == 1 && inBounds(touchevent, 50, 350, 250, 450))
            {
                game.setScreen(new GameScreen(game));
            }
            i++;
        } while (true);
    }
}
