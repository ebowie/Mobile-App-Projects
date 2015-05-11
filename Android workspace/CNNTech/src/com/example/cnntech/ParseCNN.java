package com.example.cnntech;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;

public class ParseCNN {

	static class ParseAnXML extends DefaultHandler {

		private Entry entry = null;
		private ArrayList<Entry> entries = new ArrayList<Entry>();
		private StringBuilder buffer;
		boolean reachedContent = false;

		static ArrayList<Entry> parsePosts(InputStream in) throws IOException,
				SAXException {
			ParseAnXML parser = new ParseAnXML();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getAllPosts();
		}

		public ArrayList<Entry> getAllPosts() {
			return entries;
		}

		// BEGIN OVERRIDDEN PARSING METHODS

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			buffer = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			Log.d("demo", "startElement: " + localName);

			// If the XML entry begins a new post, create a new object to store
			// data
			if (localName.equals("item")) {
				entry = new Entry();
				// reachedContent = true;
			}

		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			buffer.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);

			// Make sure that the application has actually reached the content
			/*
			 * if( ! reachedContent ) return;
			 */

			String content = buffer.toString();
			Log.d("demo", "End element: " + content);

			if (entry != null) {
				if (localName.equals("title")) {
					entry.setTitle(content);
				} else if (localName.equals("link")) {
					entry.setUrl(content);
					Log.d("test", content);
				} else if (localName.equals("item")) {
					entries.add(entry);
					Log.d("test", "Added" + entry.toString());
					entry = null;
				} 
			}

			// Reset the XML string-reading buffer for the next element
			buffer.setLength(0);
		}

	}

}
