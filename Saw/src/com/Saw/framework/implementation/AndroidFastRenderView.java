// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.Saw.framework.Screen;

// Referenced classes of package com.Saw.framework.implementation:
//            AndroidGame

public class AndroidFastRenderView extends SurfaceView
    implements Runnable
{

    Bitmap framebuffer;
    AndroidGame game;
    SurfaceHolder holder;
    Thread renderThread;
    volatile boolean running;

    public AndroidFastRenderView(AndroidGame androidgame, Bitmap bitmap)
    {
        super(androidgame);
        renderThread = null;
        running = false;
        game = androidgame;
        framebuffer = bitmap;
        holder = getHolder();
    }

    public void pause()
    {
        running = false;
        do
        {
            try
            {
                renderThread.join();
                return;
            }
            catch (InterruptedException interruptedexception) { }
        } while (true);
    }

    public void resume()
    {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void run()
    {
        Rect rect = new Rect();
        long l = System.nanoTime();
        do
        {
            do
            {
                if (!running)
                {
                    return;
                }
            } while (!holder.getSurface().isValid());
            float f1 = (float)(System.nanoTime() - l) / 1E+07F;
            l = System.nanoTime();
            float f = f1;
            if ((double)f1 > 3.1499999999999999D)
            {
                f = 3.15F;
            }
            game.getCurrentScreen().update(f);
            game.getCurrentScreen().paint(f);
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(framebuffer, null, rect, null);
            holder.unlockCanvasAndPost(canvas);
        } while (true);
    }
}
