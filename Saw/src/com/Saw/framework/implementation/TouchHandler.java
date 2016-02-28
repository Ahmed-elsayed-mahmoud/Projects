// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import java.util.List;

public interface TouchHandler
    extends android.view.View.OnTouchListener
{

    public abstract List getTouchEvents();

    public abstract int getTouchX(int i);

    public abstract int getTouchY(int i);

    public abstract boolean isTouchDown(int i);
}
