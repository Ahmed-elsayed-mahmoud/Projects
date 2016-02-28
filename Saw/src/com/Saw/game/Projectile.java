// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.graphics.Rect;

// Referenced classes of package com.Saw.game:
//            GameScreen, Heliboy, Robot

public class Projectile
{

    private Rect r;
    private Robot robot;
    private int speedX;
    private boolean visible;
    private int x;
    private int y;

    public Projectile(int i, int j)
    {
        robot = GameScreen.getRobot();
        x = i;
        y = j;
        speedX = 7;
        visible = true;
        r = new Rect(0, 0, 0, 0);
    }

    private void checkCollision()
    {
        if (Rect.intersects(r, GameScreen.hb.r))
        {
            visible = false;
            if (GameScreen.hb.health > 0)
            {
                Object obj = GameScreen.hb;
                obj.health = ((Heliboy) (obj)).health - 1;
                obj = robot;
                obj.score = ((Robot) (obj)).score + 100;
            }
            if (GameScreen.hb.health == 0)
            {
                GameScreen.hb.setCenterX(-100);
            }
        }
        if (Rect.intersects(r, GameScreen.hb2.r))
        {
            visible = false;
            if (GameScreen.hb2.health > 0)
            {
                Object obj1 = GameScreen.hb2;
                obj1.health = ((Heliboy) (obj1)).health - 1;
                obj1 = robot;
                obj1.score = ((Robot) (obj1)).score + 100;
            }
            if (GameScreen.hb2.health == 0)
            {
                GameScreen.hb2.setCenterX(-100);
            }
        }
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setSpeedX(int i)
    {
        speedX = i;
    }

    public void setVisible(boolean flag)
    {
        visible = flag;
    }

    public void setX(int i)
    {
        x = i;
    }

    public void setY(int i)
    {
        y = i;
    }

    public void update()
    {
        x = x + speedX;
        r.set(x, y, x + 10, y + 5);
        if (x > 800)
        {
            visible = false;
            r = null;
        }
        if (visible)
        {
            checkCollision();
        }
    }
}
