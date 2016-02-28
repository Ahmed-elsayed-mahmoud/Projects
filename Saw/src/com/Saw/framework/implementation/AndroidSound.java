// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.media.SoundPool;
import com.Saw.framework.Sound;

public class AndroidSound
    implements Sound
{

    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundpool, int i)
    {
        soundId = i;
        soundPool = soundpool;
    }

    public void dispose()
    {
        soundPool.unload(soundId);
    }

    public void play(float f)
    {
        soundPool.play(soundId, f, f, 0, 0, 1.0F);
    }
}
