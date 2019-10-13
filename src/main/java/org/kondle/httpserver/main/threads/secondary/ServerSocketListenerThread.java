package org.kondle.httpserver.main.threads.secondary;

import org.kondle.httpserver.executors.SocketExecutor;
import org.kondle.httpserver.http.exceptions.HttpException;
import org.kondle.httpserver.log.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenerThread extends Thread
{

	private boolean doRun;
	private ServerSocket serverSocket;
	public ServerSocketListenerThread(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
		this.doRun = true;
	}

	@Override
	public void run()
	{
		while (doRun)
		{
			try
			{
				Socket inSocket = this.serverSocket.accept();
				SocketExecutor.execute(inSocket);
			} catch (IOException | HttpException e)
			{
				Logger.log(e);
			}
		}
	}

	@Override
	public void interrupt()
	{
		this.doRun = false;
		try
		{
			this.serverSocket.close();
		} catch (IOException e)
		{
			Logger.log(e);
		} finally
		{
			super.interrupt();
		}
	}

	public int getPort()
	{
		return this.serverSocket.getLocalPort();
	}
}
