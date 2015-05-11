package com.example.pullparser;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;



public class PersonUtils {

    static class PersonPULLParser extends DefaultHandler {

        Person person;

        static Person parsePerson(InputStream in) throws IOException, SAXException{
            PersonPULLParser parser = new PersonPULLParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);


        return null;

    }
}
}