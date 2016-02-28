// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;


public interface Image
{

    public abstract void dispose();

    public abstract Graphics.ImageFormat getFormat();

    public abstract int getHeight();

    public abstract int getWidth();
}
