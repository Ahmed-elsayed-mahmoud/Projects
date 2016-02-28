// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;

import java.util.List;

public interface Input
{
    public static class TouchEvent
    {

        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;
        public static final int TOUCH_UP = 1;
        public int pointer;
        public int type;
        public int x;
        public int y;

        public TouchEvent()
        {
        }
    }


    public abstract List getTouchEvents();

    public abstract int getTouchX(int i);

    public abstract int getTouchY(int i);

    public abstract boolean isTouchDown(int i);
}
