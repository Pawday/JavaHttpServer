package org.kondle.httpserver.main.threads.secondary;

import org.kondle.httpserver.executors.SocketExecutor;
import org.kondle.httpserver.http.exceptions.HttpException;
import org.kondle.httpserver.log.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenerThread extends Thread
{

	private ServerSocket serverSocket;
	public ServerSocketListenerThread(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}

	@Override
	public void run()
	{
		while (true)
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
}
