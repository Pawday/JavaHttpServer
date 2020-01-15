package org.kondle.httpserver.http.components.header;

import java.io.IOException;
import java.io.InputStream;

public class HeaderParser
{
	public static HttpHeader parseOne(InputStream stream) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int b;
		while ((b = stream.read()) != '\n')
		{
			if (b != '\r') builder.append((char) b);
		}

		String[] keyValue = builder.toString().split(": ");

		return new HttpHeader(keyValue[0],keyValue[1]);
	}

	public static HttpHeader[] parseAll(InputStream stream) throws IOException
	{
		int b;
		char[] seq = new char[]{' ' , ' ', ' ', ' '};


		StringBuilder builder = new StringBuilder();

		while (true)
		{
			b = stream.read();
			seq[0] = seq[1];
			seq[1] = seq[2];
			seq[2] = seq[3];
			seq[3] = (char) b;
			if
			(!
				(
					seq[0] == '\r' &&
					seq[1] == '\n' &&
					seq[2] == '\r' &&
					seq[3] == '\n'
				)
			) builder.append((char) b);
			else
			{
				builder.delete(builder.length() - 4,builder.length());
				break;
			}
		}

		String[] keyValuePairs = builder.toString().split("\r\n");


		HttpHeader[] httpHeaders = new HttpHeader[keyValuePairs.length];



		for (int i = 0; i < keyValuePairs.length; i++)
		{
			String[] keyValue = keyValuePairs[i].split(": ");
			httpHeaders[i] = new HttpHeader(keyValue[0],keyValue[1]);
		}

		return httpHeaders;
	}
}

