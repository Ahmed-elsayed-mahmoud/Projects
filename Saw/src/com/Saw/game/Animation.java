// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import com.Saw.framework.Image;
import java.util.ArrayList;

public class Animation
{
    private class AnimFrame
    {

        long endTime;
        Image image;
        final Animation this$0;

        public AnimFrame(Image image1, long l)
        {
            this$0 = Animation.this;
            super();
            image = image1;
            endTime = l;
        }
    }


    private long animTime;
    private int currentFrame;
    private ArrayList frames;
    private long totalDuration;

    public Animation()
    {
        frames = new ArrayList();
        totalDuration = 0L;
        this;
        JVM INSTR monitorenter ;
        animTime = 0L;
        currentFrame = 0;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private AnimFrame getFrame(int i)
    {
        return (AnimFrame)frames.get(i);
    }

    public void addFrame(Image image, long l)
    {
        this;
        JVM INSTR monitorenter ;
        totalDuration = totalDuration + l;
        frames.add(new AnimFrame(image, totalDuration));
        this;
        JVM INSTR monitorexit ;
        return;
        image;
        throw image;
    }

    public Image getImage()
    {
        this;
        JVM INSTR monitorenter ;
        int i = frames.size();
        if (i != 0) goto _L2; else goto _L1
_L1:
        Image image = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return image;
_L2:
        image = getFrame(currentFrame).image;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void update(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (frames.size() <= 1) goto _L2; else goto _L1
_L1:
        animTime = animTime + l;
        if (animTime >= totalDuration)
        {
            animTime = animTime % totalDuration;
            currentFrame = 0;
        }
_L3:
        long l1;
        l = animTime;
        l1 = getFrame(currentFrame).endTime;
        if (l > l1)
        {
            break MISSING_BLOCK_LABEL_81;
        }
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        currentFrame = currentFrame + 1;
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }
}
