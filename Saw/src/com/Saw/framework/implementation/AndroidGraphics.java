// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.Saw.framework.Graphics;
import com.Saw.framework.Image;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.Saw.framework.implementation:
//            AndroidImage

public class AndroidGraphics
    implements Graphics
{

    AssetManager assets;
    Canvas canvas;
    Rect dstRect;
    Bitmap frameBuffer;
    Paint paint;
    Rect srcRect;

    public AndroidGraphics(AssetManager assetmanager, Bitmap bitmap)
    {
        srcRect = new Rect();
        dstRect = new Rect();
        assets = assetmanager;
        frameBuffer = bitmap;
        canvas = new Canvas(bitmap);
        paint = new Paint();
    }

    public void clearScreen(int i)
    {
        canvas.drawRGB((0xff0000 & i) >> 16, (0xff00 & i) >> 8, i & 0xff);
    }

    public void drawARGB(int i, int j, int k, int l)
    {
        paint.setStyle(android.graphics.Paint.Style.FILL);
        canvas.drawARGB(i, j, k, l);
    }

    public void drawImage(Image image, int i, int j)
    {
        canvas.drawBitmap(((AndroidImage)image).bitmap, i, j, null);
    }

    public void drawImage(Image image, int i, int j, int k, int l, int i1, int j1)
    {
        srcRect.left = k;
        srcRect.top = l;
        srcRect.right = k + i1;
        srcRect.bottom = l + j1;
        dstRect.left = i;
        dstRect.top = j;
        dstRect.right = i + i1;
        dstRect.bottom = j + j1;
        canvas.drawBitmap(((AndroidImage)image).bitmap, srcRect, dstRect, null);
    }

    public void drawLine(int i, int j, int k, int l, int i1)
    {
        paint.setColor(i1);
        canvas.drawLine(i, j, k, l, paint);
    }

    public void drawRect(int i, int j, int k, int l, int i1)
    {
        paint.setColor(i1);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        canvas.drawRect(i, j, (i + k) - 1, (j + l) - 1, paint);
    }

    public void drawScaledImage(Image image, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        srcRect.left = i1;
        srcRect.top = j1;
        srcRect.right = i1 + k1;
        srcRect.bottom = j1 + l1;
        dstRect.left = i;
        dstRect.top = j;
        dstRect.right = i + k;
        dstRect.bottom = j + l;
        canvas.drawBitmap(((AndroidImage)image).bitmap, srcRect, dstRect, null);
    }

    public void drawString(String s, int i, int j, Paint paint1)
    {
        canvas.drawText(s, i, j, paint1);
    }

    public int getHeight()
    {
        return frameBuffer.getHeight();
    }

    public int getWidth()
    {
        return frameBuffer.getWidth();
    }

    public Image newImage(String s, com.Saw.framework.Graphics.ImageFormat imageformat)
    {
        InputStream inputstream;
        Object obj1;
        Object obj;
        if (imageformat == com.Saw.framework.Graphics.ImageFormat.RGB565)
        {
            imageformat = android.graphics.Bitmap.Config.RGB_565;
        } else
        if (imageformat == com.Saw.framework.Graphics.ImageFormat.ARGB4444)
        {
            imageformat = android.graphics.Bitmap.Config.ARGB_4444;
        } else
        {
            imageformat = android.graphics.Bitmap.Config.ARGB_8888;
        }
        obj1 = new android.graphics.BitmapFactory.Options();
        obj1.inPreferredConfig = imageformat;
        obj = null;
        imageformat = null;
        inputstream = assets.open(s);
        imageformat = inputstream;
        obj = inputstream;
        obj1 = BitmapFactory.decodeStream(inputstream, null, ((android.graphics.BitmapFactory.Options) (obj1)));
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_160;
        }
        imageformat = inputstream;
        obj = inputstream;
        try
        {
            throw new RuntimeException((new StringBuilder("Couldn't load bitmap from asset '")).append(s).append("'").toString());
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = imageformat;
        }
        finally
        {
            if (obj == null) goto _L0; else goto _L0
        }
        throw new RuntimeException((new StringBuilder("Couldn't load bitmap from asset '")).append(s).append("'").toString());
        try
        {
            ((InputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (com.Saw.framework.Graphics.ImageFormat imageformat) { }
        throw s;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s) { }
        }
        if (((Bitmap) (obj1)).getConfig() == android.graphics.Bitmap.Config.RGB_565)
        {
            s = com.Saw.framework.Graphics.ImageFormat.RGB565;
        } else
        if (((Bitmap) (obj1)).getConfig() == android.graphics.Bitmap.Config.ARGB_4444)
        {
            s = com.Saw.framework.Graphics.ImageFormat.ARGB4444;
        } else
        {
            s = com.Saw.framework.Graphics.ImageFormat.ARGB8888;
        }
        return new AndroidImage(((Bitmap) (obj1)), s);
    }
}
