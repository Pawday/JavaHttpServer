package org.kondle.httpserver.main.threads.sockets;

import org.kondle.httpserver.main.threads.ThreadManager;

public class SSLSocketThread extends Thread
{
    private static boolean isStarted = false;



    @Override
    public void run()
    {

    }


    @Override
    public synchronized void start()
    {
        if (Thread.currentThread().getId() == ThreadManager.getServerThreadId() && !isStarted)
        {
            isStarted = true;
            super.start();
        }
    }
}
