// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.graphics.Rect;
import java.util.ArrayList;

// Referenced classes of package com.Saw.game:
//            GameScreen, Projectile, Background

public class Robot
{

    public static Rect footleft = new Rect(0, 0, 0, 0);
    public static Rect footright = new Rect(0, 0, 0, 0);
    public static Rect rect = new Rect(0, 0, 0, 0);
    public static Rect rect2 = new Rect(0, 0, 0, 0);
    public static Rect rect3 = new Rect(0, 0, 0, 0);
    public static Rect rect4 = new Rect(0, 0, 0, 0);
    public static Rect yellowRed = new Rect(0, 0, 0, 0);
    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    private Background bg1;
    private Background bg2;
    private int centerX;
    private int centerY;
    private boolean ducked;
    private boolean jumped;
    private boolean movingLeft;
    private boolean movingRight;
    private ArrayList projectiles;
    private boolean readyToFire;
    public int score;
    private int speedX;
    private int speedY;

    public Robot()
    {
        score = 0;
        centerX = 100;
        centerY = 377;
        jumped = false;
        movingLeft = false;
        movingRight = false;
        ducked = false;
        readyToFire = true;
        speedX = 0;
        speedY = 0;
        bg1 = GameScreen.getBg1();
        bg2 = GameScreen.getBg2();
        projectiles = new ArrayList();
    }

    private void stop()
    {
        if (!isMovingRight() && !isMovingLeft())
        {
            speedX = 0;
        }
        if (!isMovingRight() && isMovingLeft())
        {
            moveLeft();
        }
        if (isMovingRight() && !isMovingLeft())
        {
            moveRight();
        }
    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public ArrayList getProjectiles()
    {
        return projectiles;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getSpeedY()
    {
        return speedY;
    }

    public boolean isDucked()
    {
        return ducked;
    }

    public boolean isJumped()
    {
        return jumped;
    }

    public boolean isMovingLeft()
    {
        return movingLeft;
    }

    public boolean isMovingRight()
    {
        return movingRight;
    }

    public boolean isReadyToFire()
    {
        return readyToFire;
    }

    public void jump()
    {
        if (!jumped)
        {
            speedY = -15;
            jumped = true;
        }
    }

    public void moveLeft()
    {
        if (!ducked)
        {
            speedX = -5;
        }
    }

    public void moveRight()
    {
        if (!ducked)
        {
            speedX = 5;
        }
    }

    public void setCenterX(int i)
    {
        centerX = i;
    }

    public void setCenterY(int i)
    {
        centerY = i;
    }

    public void setDucked(boolean flag)
    {
        ducked = flag;
    }

    public void setJumped(boolean flag)
    {
        jumped = flag;
    }

    public void setMovingLeft(boolean flag)
    {
        movingLeft = flag;
    }

    public void setMovingRight(boolean flag)
    {
        movingRight = flag;
    }

    public void setReadyToFire(boolean flag)
    {
        readyToFire = flag;
    }

    public void setSpeedX(int i)
    {
        speedX = i;
    }

    public void setSpeedY(int i)
    {
        speedY = i;
    }

    public void shoot()
    {
        if (readyToFire)
        {
            Projectile projectile = new Projectile(centerX + 50, centerY - 25);
            projectiles.add(projectile);
        }
    }

    public void stopLeft()
    {
        setMovingLeft(false);
        stop();
    }

    public void stopRight()
    {
        setMovingRight(false);
        stop();
    }

    public void update()
    {
        if (speedX < 0)
        {
            centerX = centerX + speedX;
        }
        if (speedX == 0 || speedX < 0)
        {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
        }
        if (centerX <= 200 && speedX > 0)
        {
            centerX = centerX + speedX;
        }
        if (speedX > 0 && centerX > 200)
        {
            bg1.setSpeedX(-1);
            bg2.setSpeedX(-1);
        }
        centerY = centerY + speedY;
        speedY = speedY + 1;
        if (speedY > 3)
        {
            jumped = true;
        }
        if (centerX + speedX <= 60)
        {
            centerX = 61;
        }
        rect.set(centerX - 34, centerY - 63, centerX + 34, centerY);
        rect2.set(rect.left, rect.top + 63, rect.left + 68, rect.top + 128);
        rect3.set(rect.left - 26, rect.top + 32, rect.left, rect.top + 52);
        rect4.set(rect.left + 68, rect.top + 32, rect.left + 94, rect.top + 52);
        yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
        footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
        footright.set(centerX, centerY + 20, centerX + 50, centerY + 35);
    }

}
