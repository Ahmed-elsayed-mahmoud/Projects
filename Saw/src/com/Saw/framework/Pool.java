// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;

import java.util.ArrayList;
import java.util.List;

public class Pool
{
    public static interface PoolObjectFactory
    {

        public abstract Object createObject();
    }


    private final PoolObjectFactory factory;
    private final List freeObjects;
    private final int maxSize;

    public Pool(PoolObjectFactory poolobjectfactory, int i)
    {
        factory = poolobjectfactory;
        maxSize = i;
        freeObjects = new ArrayList(i);
    }

    public void free(Object obj)
    {
        if (freeObjects.size() < maxSize)
        {
            freeObjects.add(obj);
        }
    }

    public Object newObject()
    {
        if (freeObjects.size() == 0)
        {
            return factory.createObject();
        } else
        {
            return freeObjects.remove(freeObjects.size() - 1);
        }
    }
}
