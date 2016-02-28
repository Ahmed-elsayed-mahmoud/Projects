// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.framework;


// Referenced classes of package com.Saw.framework:
//            Music, Sound

public interface Audio
{

    public abstract Music createMusic(String s);

    public abstract Sound createSound(String s);
}
