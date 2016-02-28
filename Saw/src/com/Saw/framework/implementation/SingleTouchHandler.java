// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.view.MotionEvent;
import android.view.View;
import com.Saw.framework.Pool;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.Saw.framework.implementation:
//            TouchHandler

public class SingleTouchHandler
    implements TouchHandler
{

    boolean isTouched;
    float scaleX;
    float scaleY;
    Pool touchEventPool;
    List touchEvents;
    List touchEventsBuffer;
    int touchX;
    int touchY;

    public SingleTouchHandler(View view, float f, float f1)
    {
        touchEvents = new ArrayList();
        touchEventsBuffer = new ArrayList();
        touchEventPool = new Pool(new com.Saw.framework.Pool.PoolObjectFactory() {

            final SingleTouchHandler this$0;

            public com.Saw.framework.Input.TouchEvent createObject()
            {
                return new com.Saw.framework.Input.TouchEvent();
            }

            public volatile Object createObject()
            {
                return createObject();
            }

            
            {
                this$0 = SingleTouchHandler.this;
                super();
            }
        }, 100);
        view.setOnTouchListener(this);
        scaleX = f;
        scaleY = f1;
    }

    public List getTouchEvents()
    {
        this;
        JVM INSTR monitorenter ;
        int j = touchEvents.size();
        int i = 0;
_L2:
        if (i < j)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        List list;
        touchEvents.clear();
        touchEvents.addAll(touchEventsBuffer);
        touchEventsBuffer.clear();
        list = touchEvents;
        this;
        JVM INSTR monitorexit ;
        return list;
        touchEventPool.free((com.Saw.framework.Input.TouchEvent)touchEvents.get(i));
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getTouchX(int i)
    {
        this;
        JVM INSTR monitorenter ;
        i = touchX;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getTouchY(int i)
    {
        this;
        JVM INSTR monitorenter ;
        i = touchY;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isTouchDown(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_15;
        }
        boolean flag = isTouched;
        this;
        JVM INSTR monitorexit ;
        return flag;
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        this;
        JVM INSTR monitorenter ;
        view = (com.Saw.framework.Input.TouchEvent)touchEventPool.newObject();
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 149
    //                   0 105
    //                   1 136
    //                   2 123
    //                   3 136;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_136;
_L5:
        int i = (int)(motionevent.getX() * scaleX);
        touchX = i;
        view.x = i;
        i = (int)(motionevent.getY() * scaleY);
        touchY = i;
        view.y = i;
        touchEventsBuffer.add(view);
        this;
        JVM INSTR monitorexit ;
        return true;
_L2:
        view.type = 0;
        isTouched = true;
          goto _L5
        view;
        this;
        JVM INSTR monitorexit ;
        throw view;
_L4:
        view.type = 2;
        isTouched = true;
          goto _L5
        view.type = 1;
        isTouched = false;
          goto _L5
    }
}
