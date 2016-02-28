// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.app.Activity;
import android.content.res.AssetManager;
import android.media.SoundPool;
import com.Saw.framework.Audio;
import com.Saw.framework.Music;
import com.Saw.framework.Sound;
import java.io.IOException;

// Referenced classes of package com.Saw.framework.implementation:
//            AndroidMusic, AndroidSound

public class AndroidAudio
    implements Audio
{

    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity)
    {
        activity.setVolumeControlStream(3);
        assets = activity.getAssets();
        soundPool = new SoundPool(20, 3, 0);
    }

    public Music createMusic(String s)
    {
        AndroidMusic androidmusic;
        try
        {
            androidmusic = new AndroidMusic(assets.openFd(s));
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder("Couldn't load music '")).append(s).append("'").toString());
        }
        return androidmusic;
    }

    public Sound createSound(String s)
    {
        Object obj;
        try
        {
            obj = assets.openFd(s);
            int i = soundPool.load(((android.content.res.AssetFileDescriptor) (obj)), 0);
            obj = new AndroidSound(soundPool, i);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder("Couldn't load sound '")).append(s).append("'").toString());
        }
        return ((Sound) (obj));
    }
}
