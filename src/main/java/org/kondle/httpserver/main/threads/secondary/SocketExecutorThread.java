package org.kondle.httpserver.main.threads.secondary;

import org.kondle.httpserver.http.exceptions.HttpException;
import org.kondle.httpserver.http.implementations.HttpRequest;
import org.kondle.httpserver.log.Logger;

import java.io.IOException;
import java.net.Socket;


public class SocketExecutorThread extends Thread
{
	private Socket socket;
	public SocketExecutorThread(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run()
	{
		try
		{
			HttpRequest req = new HttpRequest(socket.getInputStream());



		} catch (IOException | HttpException e)
		{
			Logger.log(e);
		}
	}
}
