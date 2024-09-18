package com.example.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
        Log.d("MainActivity","startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
        Log.d("MainActivity","startElement");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("id".equals(nodeName)) {
            id.append(ch,start,length);
        } else if ("name".equals(nodeName)) {
            name.append(ch,start,length);
        } else if ("version".equals(nodeName)) {
            version.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)){
            Log.d("MainActivity", "id is " + id);
            Log.d("MainActivity", "name is " + name);
            Log.d("MainActivity", "version is " + version);
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
        Log.d("MainActivity","endElement");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.d("MainActivity","endDocument");
    }
}
