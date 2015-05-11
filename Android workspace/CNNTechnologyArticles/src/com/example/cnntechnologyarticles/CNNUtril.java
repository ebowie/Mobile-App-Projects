package com.example.cnntechnologyarticles;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;

public class CNNUtril {


    static class parseXML extends DefaultHandler {
        private CNN entry = null;
        private final ArrayList<CNN>entries = new ArrayList<CNN>();
        private StringBuilder buffer;
        static ArrayList<CNN> parse (InputStream in) throws IOException, SAXException {

            parseXML parser = new parseXML();
            Xml.parse(in, Xml.Encoding.UTF_8,parser);
             return parser.getParse();
             }

        public ArrayList<CNN> getParse(){
            return entries;
        }

        @Override
        public void startDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.startDocument();
            buffer = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            // TODO Auto-generated method stub
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")){
                entry = new CNN();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            // TODO Auto-generated method stub
            super.endElement(uri, localName, qName);
            if(entry != null){
                if(localName.equals("title")){
                    entry.setTitle(buffer.toString());
                } else if(localName.equals("link")){
                    entry.setLink(buffer.toString());

                }else if(localName.equals("item")){
                    entries.add(entry);
                    entry = null;

                }


            }
            buffer.setLength(0);

        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            // TODO Auto-generated method stub
            super.characters(ch, start, length);
            buffer.append(ch, start, length);
        }



    }


}
