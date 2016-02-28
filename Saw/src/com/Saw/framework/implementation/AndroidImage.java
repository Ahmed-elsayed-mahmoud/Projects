// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.graphics.Bitmap;
import com.Saw.framework.Image;

public class AndroidImage
    implements Image
{

    Bitmap bitmap;
    com.Saw.framework.Graphics.ImageFormat format;

    public AndroidImage(Bitmap bitmap1, com.Saw.framework.Graphics.ImageFormat imageformat)
    {
        bitmap = bitmap1;
        format = imageformat;
    }

    public void dispose()
    {
        bitmap.recycle();
    }

    public com.Saw.framework.Graphics.ImageFormat getFormat()
    {
        return format;
    }

    public int getHeight()
    {
        return bitmap.getHeight();
    }

    public int getWidth()
    {
        return bitmap.getWidth();
    }
}
