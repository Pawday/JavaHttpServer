package org.kondle.httpserver.http.components.header;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;
public class HttpHeadersMap implements Map<String,String>
{


    public HttpHeadersMap(InputStream in) throws IOException {
        char[] endSeq = new char[]{'\r','\n','\r','\n'};
        char[] instr = new char[]{' ',' ',' ',' '};
        boolean end = false;


        StringBuilder noSplicedHeaders = new StringBuilder();
        while (!end)
        {
            instr[0] = instr[1];
            instr[1] = instr[2];
            instr[2] = instr[3];
            instr[3] = (char) in.read();
            noSplicedHeaders.append(instr[3]);
            if (Arrays.equals(endSeq,instr)) end = true;
        }

        noSplicedHeaders.delete(noSplicedHeaders.length() - 4, noSplicedHeaders.length());

        String[] headersArray = noSplicedHeaders.toString().split("\\r\\n");

        for (String s : headersArray) {
            String[] keyVal = s.split(": ");
            add(new HttpHeader(keyVal[0], keyVal[1]));
        }

        Set<Entry<String, String>> set = this.entrySet();
    }

    public HttpHeadersMap() {
    }

    private LinkedList<HttpHeader> httpHeaderLinkedList = new LinkedList<HttpHeader>();

    public void add(HttpHeader httpHeader)
    {
        httpHeaderLinkedList.add(httpHeader);
    }

    @Override
    public int size()
    {
        return httpHeaderLinkedList.size();
    }

    @Override
    public boolean isEmpty()
    {
        return httpHeaderLinkedList.size() == 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        for (HttpHeader httpHeader : httpHeaderLinkedList) if (httpHeader.getKey().equals(key)) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value)
    {
        for (HttpHeader httpHeader : httpHeaderLinkedList) if (httpHeader.getValue().equals(value)) return true;
        return false;
    }

    @Override
    public String get(Object key)
    {
        for (HttpHeader h : httpHeaderLinkedList) if (h.getKey().equals(key)) return h.getValue();
        return null;
    }

    @Override
    public String put(String key, String value)
    {
        for (HttpHeader h : httpHeaderLinkedList)
            if (h.getKey().equals(key))
            {
                h.setValue(value);
                return value;
            }
        httpHeaderLinkedList.add(new HttpHeader(key,value));
        return value;
    }

    @Override
    public String remove(Object key)
    {
        for (HttpHeader h : httpHeaderLinkedList)
            if (h.getKey().equals(key))
            {
                httpHeaderLinkedList.remove(h);
                return h.getValue();
            }
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m)
    {
        // I do not intend to use this method because this class is an abstraction for working with headers.
    }

    @Override
    public void clear()
    {
        httpHeaderLinkedList.clear();
    }

    @Override
    public Set<String> keySet()
    {
        HashSet<String> ret =  new HashSet<String>();
        for (HttpHeader httpHeader : httpHeaderLinkedList) ret.add(httpHeader.getKey());
        return ret;
    }

    @Override
    public Collection<String> values()
    {
        Collection<String> values = new ArrayList<String>();
        for (HttpHeader httpHeader : httpHeaderLinkedList) values.add(httpHeader.getKey());
        return values;
    }

    @Override
    public Set<Entry<String, String>> entrySet()
    {
        // I do not intend to use this method because this class is an abstraction for working with headers.
        return null;
    }

    @Override
    public String toString()
    {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < this.httpHeaderLinkedList.size(); i++)
        {
            if (i != this.httpHeaderLinkedList.size() - 1)
                ret.append(this.httpHeaderLinkedList.get(i).toString()).append("\r\n");
            else
                ret.append(this.httpHeaderLinkedList.get(i).toString());
        }
        return ret.toString();
    }
}
