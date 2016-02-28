// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.graphics.Rect;
import com.Saw.framework.Image;

// Referenced classes of package com.Saw.game:
//            GameScreen, Assets, Robot, Background, 
//            Heliboy

public class Tile
{

    private Background bg;
    public int count;
    private Heliboy hb;
    private Heliboy hb2;
    private Rect r;
    private Robot robot;
    private int speedX;
    public Image tileImage;
    private int tileX;
    private int tileY;
    public int type;

    public Tile(int i, int j, int k)
    {
        count = 0;
        hb = GameScreen.getHeliboy();
        hb2 = GameScreen.getHeliboy2();
        robot = GameScreen.getRobot();
        bg = GameScreen.getBg1();
        tileX = i * 40;
        tileY = j * 40;
        type = k;
        r = new Rect();
        if (type == 5)
        {
            tileImage = Assets.tiledirt;
            return;
        }
        if (type == 8 || type == 3)
        {
            tileImage = Assets.tilegrassTop;
            return;
        }
        if (type == 4)
        {
            tileImage = Assets.tilegrassLeft;
            return;
        }
        if (type == 6)
        {
            tileImage = Assets.tilegrassRight;
            return;
        }
        if (type == 2)
        {
            tileImage = Assets.tilegrassBot;
            return;
        } else
        {
            type = 0;
            return;
        }
    }

    public void checkSideCollision(Rect rect, Rect rect1, Rect rect2, Rect rect3)
    {
        if (type == 5 || type == 2 || type == 0) goto _L2; else goto _L1
_L1:
        if (!Rect.intersects(rect, r)) goto _L4; else goto _L3
_L3:
        robot.setCenterX(tileX + 102);
        robot.setSpeedX(0);
_L9:
        if (!Rect.intersects(rect1, r)) goto _L6; else goto _L5
_L5:
        robot.setCenterX(tileX - 62);
        robot.setSpeedX(0);
_L2:
        return;
_L4:
        if (Rect.intersects(rect2, r))
        {
            robot.setCenterX(tileX + 85);
            robot.setSpeedX(0);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (!Rect.intersects(rect3, r)) goto _L2; else goto _L7
_L7:
        robot.setCenterX(tileX - 45);
        robot.setSpeedX(0);
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public void checkVerticalCollision(Rect rect, Rect rect1)
    {
        Rect.intersects(rect, r);
        if (Rect.intersects(rect1, r) && type == 8)
        {
            robot.setJumped(false);
            robot.setSpeedY(0);
            robot.setCenterY(tileY - 63);
        }
    }

    public Image getTileImage()
    {
        return tileImage;
    }

    public int getTileX()
    {
        return tileX;
    }

    public int getTileY()
    {
        return tileY;
    }

    public void setTileImage(Image image)
    {
        tileImage = image;
    }

    public void setTileX(int i)
    {
        tileX = i;
    }

    public void setTileY(int i)
    {
        tileY = i;
    }

    public void update()
    {
        speedX = bg.getSpeedX() * 5;
        tileX = tileX + speedX;
        r.set(tileX, tileY, tileX + 40, tileY + 40);
        if (Rect.intersects(r, Robot.yellowRed) && type != 0)
        {
            checkVerticalCollision(Robot.rect, Robot.rect2);
            checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
        }
        count = count + 1;
    }
}
