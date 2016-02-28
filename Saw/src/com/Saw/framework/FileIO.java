// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;

import android.content.SharedPreferences;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO
{

    public abstract SharedPreferences getSharedPref();

    public abstract InputStream readAsset(String s)
        throws IOException;

    public abstract InputStream readFile(String s)
        throws IOException;

    public abstract OutputStream writeFile(String s)
        throws IOException;
}
