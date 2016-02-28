// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.Saw.framework.Audio;
import com.Saw.framework.FileIO;
import com.Saw.framework.Game;
import com.Saw.framework.Graphics;
import com.Saw.framework.Input;
import com.Saw.framework.Screen;

// Referenced classes of package com.Saw.framework.implementation:
//            AndroidFastRenderView, AndroidGraphics, AndroidFileIO, AndroidAudio, 
//            AndroidInput

public abstract class AndroidGame extends Activity
    implements Game
{

    Audio audio;
    FileIO fileIO;
    Graphics graphics;
    Input input;
    AndroidFastRenderView renderView;
    Screen screen;
    android.os.PowerManager.WakeLock wakeLock;

    public AndroidGame()
    {
    }

    public Audio getAudio()
    {
        return audio;
    }

    public Screen getCurrentScreen()
    {
        return screen;
    }

    public FileIO getFileIO()
    {
        return fileIO;
    }

    public Graphics getGraphics()
    {
        return graphics;
    }

    public Input getInput()
    {
        return input;
    }

    public void onCreate(Bundle bundle)
    {
        char c = '\u0320';
        int i = 1;
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        float f;
        float f1;
        int j;
        if (getResources().getConfiguration().orientation != 1)
        {
            i = 0;
        }
        if (i != 0)
        {
            j = 480;
        } else
        {
            j = 800;
        }
        if (i != 0)
        {
            i = c;
        } else
        {
            i = 480;
        }
        bundle = Bitmap.createBitmap(j, i, android.graphics.Bitmap.Config.RGB_565);
        f = (float)j / (float)getWindowManager().getDefaultDisplay().getWidth();
        f1 = (float)i / (float)getWindowManager().getDefaultDisplay().getHeight();
        renderView = new AndroidFastRenderView(this, bundle);
        graphics = new AndroidGraphics(getAssets(), bundle);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, f, f1);
        screen = getInitScreen();
        setContentView(renderView);
        wakeLock = ((PowerManager)getSystemService("power")).newWakeLock(26, "MyGame");
    }

    public void onPause()
    {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (isFinishing())
        {
            screen.dispose();
        }
    }

    public void onResume()
    {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    public void setScreen(Screen screen1)
    {
        if (screen1 == null)
        {
            throw new IllegalArgumentException("Screen must not be null");
        } else
        {
            screen.pause();
            screen.dispose();
            screen1.resume();
            screen1.update(0.0F);
            screen = screen1;
            return;
        }
    }
}
