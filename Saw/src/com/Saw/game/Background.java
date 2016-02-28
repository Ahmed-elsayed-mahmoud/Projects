// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;


public class Background
{

    private int bgX;
    private int bgY;
    private int speedX;

    public Background(int i, int j)
    {
        bgX = i;
        bgY = j;
        speedX = 0;
    }

    public int getBgX()
    {
        return bgX;
    }

    public int getBgY()
    {
        return bgY;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public void setBgX(int i)
    {
        bgX = i;
    }

    public void setBgY(int i)
    {
        bgY = i;
    }

    public void setSpeedX(int i)
    {
        speedX = i;
    }

    public void update()
    {
        bgX = bgX + speedX;
        if (bgX <= -2160)
        {
            bgX = bgX + 4320;
        }
    }
}
