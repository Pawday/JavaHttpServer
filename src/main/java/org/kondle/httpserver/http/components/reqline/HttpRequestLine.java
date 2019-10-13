package org.kondle.httpserver.http.components.reqline;

import org.kondle.httpserver.http.exceptions.HttpRequestLineException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HttpRequestLine
{

    public HttpRequestLine(HttpReqMethod reqMethod, String path, String httpVersion)
    {
        this.httpReqMethod = reqMethod;
        this.path = path;
        this.httpVersion = httpVersion;
    }

    public enum HttpReqMethod { GET, POST, PUT, CUT, DELETE }

    private HttpReqMethod httpReqMethod;
    private String path;
    private String httpVersion;



    public HttpRequestLine(InputStream stream) throws HttpRequestLineException, IOException
    {
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        StringBuilder reqLine = new StringBuilder();
        int b;

        //TODO: export httpReqLineLengthLimit to configs
        long limit = 1000;
        long limitCounter = 0;

        while ((b = stream.read()) != '\n')
        {
            if (limitCounter < limit)
            {
                reqLine.append((char)b);
                limitCounter++;
            } else throw new HttpRequestLineException("HTTP request line limit exceeded");
        }

        if (reqLine.charAt(reqLine.length() - 1) == '\r') reqLine.delete(reqLine.length() - 1,reqLine.length());

        String[] reqLineArray = reqLine.toString().split(" ");

        if (reqLineArray.length != 3)
        {
            throw new HttpRequestLineException("illegal HTTP request");
        }

        String method = reqLineArray[0];


        if (method.equals("GET")) this.httpReqMethod = HttpReqMethod.GET;
        if (method.equals("POST")) this.httpReqMethod =HttpReqMethod.POST;
        if (method.equals("PUT")) this.httpReqMethod = HttpReqMethod.PUT;
        if (method.equals("CUT")) this.httpReqMethod = HttpReqMethod.CUT;
        if (method.equals("DELETE")) this.httpReqMethod = HttpReqMethod.DELETE;


        if (this.httpReqMethod == null)
        {
            stream.close();
            throw new HttpRequestLineException("illegal HTTP request method: \"" + method  + "\"");
        }

        this.path = reqLineArray[1];
        this.httpVersion = reqLineArray[2];


    }
    public HttpReqMethod getHttpReqMethod()
    {
        return httpReqMethod;
    }
    public String getPath()
    {
        return path;
    }
    public String getHttpVersion()
    {
        return httpVersion;
    }

}