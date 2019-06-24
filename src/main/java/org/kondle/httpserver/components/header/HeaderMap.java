package org.kondle.http.components.header;

import java.util.*;

public class HeaderMap implements Map<String,String>
{

    private LinkedList<Header> headerLinkedList = new LinkedList<Header>();

    @Override
    public int size()
    {
        return headerLinkedList.size();
    }

    @Override
    public boolean isEmpty()
    {
        return headerLinkedList.size() == 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        for (Header header : headerLinkedList) if (header.getKey().equals(key)) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value)
    {
        for (Header header : headerLinkedList) if (header.getValue().equals(value)) return true;
        return false;
    }

    @Override
    public String get(Object key)
    {
        for (Header h : headerLinkedList) if (h.getKey().equals(key)) return h.getValue();
        return null;
    }

    @Override
    public String put(String key, String value)
    {
        for (Header h : headerLinkedList)
            if (h.getKey().equals(key))
            {
                h.setValue(value);
                return value;
            }
        headerLinkedList.add(new Header(key,value));
        return value;
    }

    @Override
    public String remove(Object key)
    {
        for (Header h : headerLinkedList)
            if (h.getKey().equals(key))
            {
                headerLinkedList.remove(h);
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
        headerLinkedList.clear();
    }

    @Override
    public Set<String> keySet()
    {
        HashSet<String> ret =  new HashSet<String>();
        for (Header header : headerLinkedList) ret.add(header.getKey());
        return ret;
    }

    @Override
    public Collection<String> values()
    {
        Collection<String> values = new ArrayList<String>();
        for (Header header : headerLinkedList) values.add(header.getKey());
        return values;
    }

    @Override
    public Set<Entry<String, String>> entrySet()
    {
        // I do not intend to use this method because this class is an abstraction for working with headers.
        return null;
    }
}
