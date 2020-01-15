package org.kondle.httpserver.http.implementations;

import org.kondle.httpserver.http.components.header.HttpHeadersMap;
import org.kondle.httpserver.http.components.reqline.HttpRequestLine;
import org.kondle.httpserver.http.exceptions.HttpException;

import java.io.IOException;
import java.io.InputStream;


public class HttpRequest
{
    public HttpRequestLine requestLine;
    private HttpHeadersMap headers;
    private boolean hasSize;
    private int size;
    private byte[] body;
    private InputStream inputStream;
    private boolean keepAlive;

    public HttpRequest(InputStream inputStream) throws IOException, HttpException
    {
        this.requestLine = new HttpRequestLine(inputStream);
        this.headers = new HttpHeadersMap(inputStream);

        if (this.headers.containsKey("Connection"))
            this.keepAlive = this.headers.get("Connection").equals("keep-alive");

        if (this.headers.containsKey("Content-Length"))
        {
            this.hasSize = true;
            this.size = Integer.parseInt(this.headers.get("Content-Length"));
            this.body = new byte[this.size];
            for (int i = 0; i < this.body.length; i++)
                this.body[i] = (byte) inputStream.read();
        }
        else
        {
            this.size = 0;
            this.hasSize = false;
        }
        //TODO: implement getInputStream and getBody (return text if has Size) {will need to consider the: Conn--: keep-alive}
    }

    public HttpHeadersMap getHeaders()
    {
        return headers;
    }
}
