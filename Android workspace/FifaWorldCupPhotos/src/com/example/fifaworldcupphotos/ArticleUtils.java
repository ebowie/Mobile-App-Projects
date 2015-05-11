package com.example.fifaworldcupphotos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;


public class ArticleUtils {
	
	
	static class ArticleSAXParser extends DefaultHandler {
		private Article article = null;
		private ArrayList<Article> articles;
		private StringBuilder stringBuffer;
		int passedFirstItem = 0;
		boolean passedFirst = false;
		
		static ArrayList<Article> parseArticles(InputStream in) throws IOException,
			SAXException {
			ArticleSAXParser parser = new ArticleSAXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getArticle();
		}
		
		public ArrayList<Article> getArticle() {
			return articles;
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			stringBuffer.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
			
			// Log.d("test", qName  );
			if (localName.equals("title")) {
				if(!passedFirst) {
					passedFirst = true;
				} else {
					article.setTitle( stringBuffer.toString());
				}
			} else if (qName.equals( "media:description" ) ) {
				article.setDescription(stringBuffer.toString());

			} else if (qName.equals( "media:content" )) {
				article.setImage(stringBuffer.toString());
			} else if ( localName.equals( "item" ) ) {
				articles.add( article );
			}
			stringBuffer.setLength(0);
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
			if(localName.equals("item")){
				article = new Article();
			} else if (attributes.getValue("url") != null) {
				article.setImage(attributes.getValue("url"));
			}
		}
		
	}
	
}
