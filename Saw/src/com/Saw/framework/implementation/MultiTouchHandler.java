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

public class MultiTouchHandler
    implements TouchHandler
{

    private static final int MAX_TOUCHPOINTS = 10;
    int id[];
    boolean isTouched[];
    float scaleX;
    float scaleY;
    Pool touchEventPool;
    List touchEvents;
    List touchEventsBuffer;
    int touchX[];
    int touchY[];

    public MultiTouchHandler(View view, float f, float f1)
    {
        isTouched = new boolean[10];
        touchX = new int[10];
        touchY = new int[10];
        id = new int[10];
        touchEvents = new ArrayList();
        touchEventsBuffer = new ArrayList();
        touchEventPool = new Pool(new com.Saw.framework.Pool.PoolObjectFactory() {

            final MultiTouchHandler this$0;

            public com.Saw.framework.Input.TouchEvent createObject()
            {
                return new com.Saw.framework.Input.TouchEvent();
            }

            public volatile Object createObject()
            {
                return createObject();
            }

            
            {
                this$0 = MultiTouchHandler.this;
                super();
            }
        }, 100);
        view.setOnTouchListener(this);
        scaleX = f;
        scaleY = f1;
    }

    private int getIndex(int i)
    {
        int j = 0;
_L6:
        if (j < 10) goto _L2; else goto _L1
_L1:
        int k = -1;
_L4:
        return k;
_L2:
        k = j;
        if (id[j] == i) goto _L4; else goto _L3
_L3:
        j++;
        if (true) goto _L6; else goto _L5
_L5:
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
        i = getIndex(i);
        if (i >= 0 && i < 10)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        this;
        JVM INSTR monitorexit ;
        return 0;
        i = touchX[i];
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
        i = getIndex(i);
        if (i >= 0 && i < 10)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        this;
        JVM INSTR monitorexit ;
        return 0;
        i = touchY[i];
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
        i = getIndex(i);
        if (i >= 0 && i < 10)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        boolean flag = isTouched[i];
        this;
        JVM INSTR monitorexit ;
        return flag;
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
        int j;
        int k;
        int l;
        j = motionevent.getAction();
        k = motionevent.getAction();
        l = motionevent.getPointerCount();
        int i = 0;
_L5:
        if (i < 10)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        this;
        JVM INSTR monitorexit ;
        return true;
        if (i < l) goto _L2; else goto _L1
_L1:
        isTouched[i] = false;
        id[i] = -1;
          goto _L3
_L2:
        int i1 = motionevent.getPointerId(i);
        if (motionevent.getAction() != 2 && i != (k & 0xff00) >> 8) goto _L3; else goto _L4
_L6:
        int ai[];
        int j1;
        view = (com.Saw.framework.Input.TouchEvent)touchEventPool.newObject();
        view.type = 0;
        view.pointer = i1;
        ai = touchX;
        j1 = (int)(motionevent.getX(i) * scaleX);
        ai[i] = j1;
        view.x = j1;
        ai = touchY;
        j1 = (int)(motionevent.getY(i) * scaleY);
        ai[i] = j1;
        view.y = j1;
        isTouched[i] = true;
        id[i] = i1;
        touchEventsBuffer.add(view);
          goto _L3
        view;
        this;
        JVM INSTR monitorexit ;
        throw view;
_L7:
        view = (com.Saw.framework.Input.TouchEvent)touchEventPool.newObject();
        view.type = 1;
        view.pointer = i1;
        ai = touchX;
        i1 = (int)(motionevent.getX(i) * scaleX);
        ai[i] = i1;
        view.x = i1;
        ai = touchY;
        i1 = (int)(motionevent.getY(i) * scaleY);
        ai[i] = i1;
        view.y = i1;
        isTouched[i] = false;
        id[i] = -1;
        touchEventsBuffer.add(view);
          goto _L3
_L8:
        view = (com.Saw.framework.Input.TouchEvent)touchEventPool.newObject();
        view.type = 2;
        view.pointer = i1;
        ai = touchX;
        j1 = (int)(motionevent.getX(i) * scaleX);
        ai[i] = j1;
        view.x = j1;
        ai = touchY;
        j1 = (int)(motionevent.getY(i) * scaleY);
        ai[i] = j1;
        view.y = j1;
        isTouched[i] = true;
        id[i] = i1;
        touchEventsBuffer.add(view);
_L3:
        i++;
          goto _L5
_L4:
        j & 0xff;
        JVM INSTR tableswitch 0 6: default 496
    //                   0 92
    //                   1 212
    //                   2 326
    //                   3 212
    //                   4 496
    //                   5 92
    //                   6 212;
           goto _L3 _L6 _L7 _L8 _L7 _L3 _L6 _L7
    }
}
