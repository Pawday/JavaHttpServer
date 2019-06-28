package org.kondle.httpserver.main.threads;

public class ThreadManager
{
    private static long mainThreadId;
    private static long controlThreadId;
    private static long serverThreadId;

    private static boolean serverInited = false;
    private static boolean controlInited = false;


    static
    {
        mainThreadId = Thread.currentThread().getId();
    }

    public static long getMainThreadId() { return mainThreadId; }
    public static void setServerThreadId(long id)
    {
        if (Thread.currentThread().getId() == mainThreadId && !serverInited)
        {
            serverThreadId = id;
            serverInited = true;
        }
    }

    public static void setControlThreadId(long id)
    {
        if (Thread.currentThread().getId() == mainThreadId && !controlInited)
        {
            controlThreadId = id;
            controlInited = true;
        }
    }


    public static long getServerThreadId() { return serverThreadId; }
    public static long getControlThreadId() { return controlThreadId; }
}
