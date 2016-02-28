// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.graphics.Rect;

// Referenced classes of package com.Saw.game:
//            GameScreen, Robot, Background

public class Enemy
{

    private Background bg;
    private int centerX;
    private int centerY;
    public int health;
    private int movementSpeed;
    private int power;
    public Rect r;
    private Robot robot;
    private int speedX;

    public Enemy()
    {
        bg = GameScreen.getBg1();
        robot = GameScreen.getRobot();
        r = new Rect(0, 0, 0, 0);
        health = 5;
    }

    private void checkCollision()
    {
        if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2) || Rect.intersects(r, Robot.rect3) || Rect.intersects(r, Robot.rect4))
        {
            Robot robot1 = robot;
            robot1.score = robot1.score - 1;
        }
    }

    public void attack()
    {
    }

    public void die()
    {
    }

    public void follow()
    {
        if (centerX < -95 || centerX > 810)
        {
            movementSpeed = 0;
            return;
        }
        if (Math.abs(robot.getCenterX() - centerX) < 5)
        {
            movementSpeed = 0;
            return;
        }
        if (robot.getCenterX() >= centerX)
        {
            movementSpeed = 1;
            return;
        } else
        {
            movementSpeed = -1;
            return;
        }
    }

    public Background getBg()
    {
        return bg;
    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public int getPower()
    {
        return power;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public void setBg(Background background)
    {
        bg = background;
    }

    public void setCenterX(int i)
    {
        centerX = i;
    }

    public void setCenterY(int i)
    {
        centerY = i;
    }

    public void setPower(int i)
    {
        power = i;
    }

    public void setSpeedX(int i)
    {
        speedX = i;
    }

    public void update()
    {
        follow();
        centerX = centerX + speedX;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 25);
        if (Rect.intersects(r, Robot.yellowRed))
        {
            checkCollision();
        }
    }
}
