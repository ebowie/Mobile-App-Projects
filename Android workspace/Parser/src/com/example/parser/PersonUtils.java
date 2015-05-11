package com.example.parser;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;

public class PersonUtils {

    static class PersonSAXParser extends DefaultHandler {

        Person person;
           StringBuilder buffer;
        static Person parsePerson(InputStream in) throws IOException, SAXException{
            PersonSAXParser parser = new PersonSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);



            return parser.getPerson();
        }

        public Person getPerson() {
            return person;
        }

        @Override
        public void startDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.startDocument();
            buffer = new StringBuilder();

        }

        @Override
        public void endDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            // TODO Auto-generated method stub
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("person")){
                person = new Person();
                person.setId(attributes.getValue("id"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            // TODO Auto-generated method stub
            super.endElement(uri, localName, qName);
            if(localName.equals("name")){
                person.setName(buffer.toString());

            } else if(localName.equals("department")){
                person.setAge(buffer.toString());

            }else if(localName.equals("age")){
                person.setDepartment(buffer.toString());

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
