// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.Saw.framework.Music;
import java.io.IOException;

public class AndroidMusic
    implements Music, android.media.MediaPlayer.OnCompletionListener, android.media.MediaPlayer.OnSeekCompleteListener, android.media.MediaPlayer.OnPreparedListener, android.media.MediaPlayer.OnVideoSizeChangedListener
{

    boolean isPrepared;
    MediaPlayer mediaPlayer;

    public AndroidMusic(AssetFileDescriptor assetfiledescriptor)
    {
        isPrepared = false;
        mediaPlayer = new MediaPlayer();
        try
        {
            mediaPlayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (AssetFileDescriptor assetfiledescriptor)
        {
            throw new RuntimeException("Couldn't load music");
        }
    }

    public void dispose()
    {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public boolean isLooping()
    {
        return mediaPlayer.isLooping();
    }

    public boolean isPlaying()
    {
        return mediaPlayer.isPlaying();
    }

    public boolean isStopped()
    {
        return !isPrepared;
    }

    public void onCompletion(MediaPlayer mediaplayer)
    {
        this;
        JVM INSTR monitorenter ;
        isPrepared = false;
        this;
        JVM INSTR monitorexit ;
        return;
        mediaplayer;
        this;
        JVM INSTR monitorexit ;
        throw mediaplayer;
    }

    public void onPrepared(MediaPlayer mediaplayer)
    {
        this;
        JVM INSTR monitorenter ;
        isPrepared = true;
        this;
        JVM INSTR monitorexit ;
        return;
        mediaplayer;
        this;
        JVM INSTR monitorexit ;
        throw mediaplayer;
    }

    public void onSeekComplete(MediaPlayer mediaplayer)
    {
    }

    public void onVideoSizeChanged(MediaPlayer mediaplayer, int i, int j)
    {
    }

    public void pause()
    {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    public void play()
    {
        if (mediaPlayer.isPlaying())
        {
            return;
        }
        this;
        JVM INSTR monitorenter ;
        if (!isPrepared)
        {
            mediaPlayer.prepare();
        }
        mediaPlayer.start();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (IllegalStateException illegalstateexception)
        {
            illegalstateexception.printStackTrace();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return;
    }

    public void seekBegin()
    {
        mediaPlayer.seekTo(0);
    }

    public void setLooping(boolean flag)
    {
        mediaPlayer.setLooping(flag);
    }

    public void setVolume(float f)
    {
        mediaPlayer.setVolume(f, f);
    }

    public void stop()
    {
        if (!mediaPlayer.isPlaying())
        {
            break MISSING_BLOCK_LABEL_32;
        }
        mediaPlayer.stop();
        this;
        JVM INSTR monitorenter ;
        isPrepared = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
