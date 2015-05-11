package com.scripture.celebratescripture;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;

public class SAXParseXML {
	
	static class ParseAnXML extends DefaultHandler {
		
		private BlogPost myPost;
		private ArrayList<BlogPost> allPosts = new ArrayList<BlogPost>();
		private StringBuilder buffer;
		boolean reachedContent = false;
		public String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "August", "September", "October", "November", "December" };
		
		static ArrayList<BlogPost> parsePosts( InputStream in ) throws IOException, SAXException {
			ParseAnXML parser = new ParseAnXML();
			Xml.parse( in, Xml.Encoding.UTF_8, parser );
			return parser.getAllPosts();
		}
		
		public ArrayList<BlogPost> getAllPosts() {
			return allPosts;
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
			Log.d("demo", "startElement: " + localName );
			
			// If the XML entry begins a new post, create a new object to store data
			if( localName.equals( "entry" ) ) {
				myPost = new BlogPost();
				reachedContent = true;
			}
			
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			buffer.append( ch, start, length );
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			
			// Make sure that the application has actually reached the content
			if( ! reachedContent )
				return;
			
			String content = buffer.toString();
			
			if( localName.equals( "published" ) ) {
				String month = months[ Integer.parseInt( content.substring( 5, 7 ) ) ];
				String date = month + " " + content.substring( 8, 10 ) + ", " + content.substring( 0, 4 );
				myPost.setDate( date );
			} else if ( localName.equals( "title" ) ) {
				myPost.setTitle( content );
			} else if ( localName.equals( "content" ) ) {
				String extraData[] = content.substring( content.indexOf( "FeedValues" ) + 11, content.indexOf( "-EndFeed" ) ).split( "\\|" );
				
				myPost.setPassage( extraData[0] );
				myPost.setPerson( extraData[1] );
				myPost.setThumbnail( extraData[5] );
				myPost.setLink( extraData[4] );
				myPost.setTime( extraData[7] );
				myPost.setMessage( content.substring( 0, content.indexOf( "FeedValues" ) ) );
			} else if ( localName.equals( "entry" ) ) {
				allPosts.add( myPost );
			}
			
			// Reset the XML string-reading buffer for the next element
			buffer.setLength( 0 );
		}		
		
	}

}
