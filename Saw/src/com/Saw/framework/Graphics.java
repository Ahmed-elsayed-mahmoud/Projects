// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;

import android.graphics.Paint;

// Referenced classes of package com.Saw.framework:
//            Image

public interface Graphics
{
    public static final class ImageFormat extends Enum
    {

        public static final ImageFormat ARGB4444;
        public static final ImageFormat ARGB8888;
        private static final ImageFormat ENUM$VALUES[];
        public static final ImageFormat RGB565;

        public static ImageFormat valueOf(String s)
        {
            return (ImageFormat)Enum.valueOf(com/Saw/framework/Graphics$ImageFormat, s);
        }

        public static ImageFormat[] values()
        {
            ImageFormat aimageformat[] = ENUM$VALUES;
            int i = aimageformat.length;
            ImageFormat aimageformat1[] = new ImageFormat[i];
            System.arraycopy(aimageformat, 0, aimageformat1, 0, i);
            return aimageformat1;
        }

        static 
        {
            ARGB8888 = new ImageFormat("ARGB8888", 0);
            ARGB4444 = new ImageFormat("ARGB4444", 1);
            RGB565 = new ImageFormat("RGB565", 2);
            ENUM$VALUES = (new ImageFormat[] {
                ARGB8888, ARGB4444, RGB565
            });
        }

        private ImageFormat(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract void clearScreen(int i);

    public abstract void drawARGB(int i, int j, int k, int l);

    public abstract void drawImage(Image image, int i, int j);

    public abstract void drawImage(Image image, int i, int j, int k, int l, int i1, int j1);

    public abstract void drawLine(int i, int j, int k, int l, int i1);

    public abstract void drawRect(int i, int j, int k, int l, int i1);

    public abstract void drawString(String s, int i, int j, Paint paint);

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract Image newImage(String s, ImageFormat imageformat);
}
