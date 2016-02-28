// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.content.Context;
import android.view.View;
import com.Saw.framework.Input;
import java.util.List;

// Referenced classes of package com.Saw.framework.implementation:
//            SingleTouchHandler, MultiTouchHandler, TouchHandler

public class AndroidInput
    implements Input
{

    TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float f, float f1)
    {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) < 5)
        {
            touchHandler = new SingleTouchHandler(view, f, f1);
            return;
        } else
        {
            touchHandler = new MultiTouchHandler(view, f, f1);
            return;
        }
    }

    public List getTouchEvents()
    {
        return touchHandler.getTouchEvents();
    }

    public int getTouchX(int i)
    {
        return touchHandler.getTouchX(i);
    }

    public int getTouchY(int i)
    {
        return touchHandler.getTouchY(i);
    }

    public boolean isTouchDown(int i)
    {
        return touchHandler.isTouchDown(i);
    }
}
