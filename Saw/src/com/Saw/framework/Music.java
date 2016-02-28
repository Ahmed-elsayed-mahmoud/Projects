// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;


public interface Music
{

    public abstract void dispose();

    public abstract boolean isLooping();

    public abstract boolean isPlaying();

    public abstract boolean isStopped();

    public abstract void pause();

    public abstract void play();

    public abstract void seekBegin();

    public abstract void setLooping(boolean flag);

    public abstract void setVolume(float f);

    public abstract void stop();
}
