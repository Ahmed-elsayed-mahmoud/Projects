// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.content.res.Resources;
import android.util.Log;
import com.Saw.framework.Music;
import com.Saw.framework.Screen;
import com.Saw.framework.implementation.AndroidGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Referenced classes of package com.Saw.game:
//            Assets, SplashLoadingScreen

public class SampleGame extends AndroidGame
{

    public static String map;
    boolean firstTimeCreate;

    public SampleGame()
    {
        firstTimeCreate = true;
    }

    private static String convertStreamToString(InputStream inputstream)
    {
        StringBuilder stringbuilder;
        BufferedReader bufferedreader;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
_L2:
        String s = bufferedreader.readLine();
label0:
        {
            {
                if (s != null)
                {
                    break label0;
                }
                Exception exception;
                IOException ioexception;
                try
                {
                    inputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch (InputStream inputstream)
                {
                    Log.w("LOG", inputstream.getMessage());
                }
            }
            return stringbuilder.toString();
        }
        stringbuilder.append((new StringBuilder(String.valueOf(s))).append("\n").toString());
        continue; /* Loop/switch isn't completed */
        ioexception;
        Log.w("LOG", ioexception.getMessage());
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            Log.w("LOG", inputstream.getMessage());
        }
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_37;
        }
        exception;
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            Log.w("LOG", inputstream.getMessage());
        }
        throw exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public Screen getInitScreen()
    {
        if (firstTimeCreate)
        {
            Assets.load(this);
            firstTimeCreate = false;
        }
        map = convertStreamToString(getResources().openRawResource(0x7f040001));
        return new SplashLoadingScreen(this);
    }

    public void onBackPressed()
    {
        getCurrentScreen().backButton();
    }

    public void onPause()
    {
        super.onPause();
        Assets.theme.pause();
    }

    public void onResume()
    {
        super.onResume();
        Assets.theme.play();
    }
}
