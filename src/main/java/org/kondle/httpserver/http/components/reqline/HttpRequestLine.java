package org.kondle.httpserver.http.components.reqline;

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
    public void setHttpVersion(String httpVersion)
    {
        this.httpVersion = httpVersion;
    }

}
