// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework.implementation;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import com.Saw.framework.FileIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidFileIO
    implements FileIO
{

    AssetManager assets;
    Context context;
    String externalStoragePath;

    public AndroidFileIO(Context context1)
    {
        context = context1;
        assets = context1.getAssets();
        externalStoragePath = (new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()))).append(File.separator).toString();
    }

    public SharedPreferences getSharedPref()
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public InputStream readAsset(String s)
        throws IOException
    {
        return assets.open(s);
    }

    public InputStream readFile(String s)
        throws IOException
    {
        return new FileInputStream((new StringBuilder(String.valueOf(externalStoragePath))).append(s).toString());
    }

    public OutputStream writeFile(String s)
        throws IOException
    {
        return new FileOutputStream((new StringBuilder(String.valueOf(externalStoragePath))).append(s).toString());
    }
}
