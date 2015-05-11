package com.example.saxparserdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;

public class PersonUtils {

	static class PersonsSAXParser extends DefaultHandler {
		private Person person = null;
		private ArrayList<Person> persons;
		private StringBuilder stringBuffer;

		static ArrayList<Person> parsePersons(InputStream in) throws IOException,
				SAXException {
			PersonsSAXParser parser = new PersonsSAXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getPersons();
		}
		
		public ArrayList<Person> getPersons() {
			return persons;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			stringBuffer = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			Log.d("demo", "startElement: " + localName);
			if(localName.equals("persons")){
				persons = new ArrayList<Person>();				
			} else if (localName.equals("person")) {
				person = new Person();
				if (attributes.getValue("id") != null) {
					person.setId(attributes.getValue("id"));
				}
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
			Log.d("demo", "endElement: " + localName);
			if (localName.equals("name")) {
				person.setName(stringBuffer.toString().trim());
			} else if (localName.equals("age")) {
				if(stringBuffer.toString() != null){
					person.setAge(Integer.valueOf(stringBuffer.toString().trim()).intValue());
				}
			} else if (localName.equals("department")) {
				person.setDepartment(stringBuffer.toString().trim());
			} else if (localName.equals("person")){
				persons.add(person);
			}
			stringBuffer.setLength(0);
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			stringBuffer.append(ch, start, length);
		}

	}
	
	
	static class PersonSAXParser extends DefaultHandler {
		private Person person = null;
		private StringBuilder stringBuffer;

		static Person parsePerson(InputStream in) throws IOException,
				SAXException {
			PersonSAXParser parser = new PersonSAXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getPerson();
		}

		public Person getPerson() {
			return person;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			stringBuffer = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			Log.d("demo", "startElement: " + localName);
			if (localName.equals("person")) {
				person = new Person();
				if (attributes.getValue("id") != null) {
					person.setId(attributes.getValue("id"));
				}
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
			Log.d("demo", "endElement: " + localName);
			if (localName.equals("name")) {
				person.setName(stringBuffer.toString().trim());
			} else if (localName.equals("age")) {
				if(stringBuffer.toString() != null){
					person.setAge(Integer.valueOf(stringBuffer.toString().trim()).intValue());
				}
			} else if (localName.equals("department")) {
				person.setDepartment(stringBuffer.toString().trim());
			}
			stringBuffer.setLength(0);
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			stringBuffer.append(ch, start, length);
		}

	}
}