package com.example.fifaworlcupphotos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;

public class ArticleUtil {

    static class ArticleSAXParser extends DefaultHandler {
        private Article article = null;
        private final ArrayList<Article> articles= new ArrayList<Article>();
        private StringBuilder stringBuffer;


        static ArrayList<Article> parseArticles(InputStream in) throws IOException, SAXException {
            ArticleSAXParser parser = new ArticleSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8,parser);
            return parser.getArticle();

        }
        public ArrayList<Article> getArticle() {
            return articles;
        }
        @Override
        public void startDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.startDocument();
            stringBuffer = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            // TODO Auto-generated method stub
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")){
                article = new Article();



            }
            if(qName.equals("media:content")){
                if(attributes.getValue("url") != null){
                article.setImage(attributes.getValue("url"));
                }
            }


        }
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            // TODO Auto-generated method stub
            super.endElement(uri, localName, qName);

            if(article != null) {
            if (localName.equals("title")) {

                    article.setTitle( stringBuffer.toString());

            } else if (qName.equals( "media:description" ) ) {
                article.setDescription(stringBuffer.toString());

            }  else if ( localName.equals("item")) {
                articles.add(article);
                article = null;
            }
            }
            stringBuffer.setLength(0);

        }
        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            // TODO Auto-generated method stub
            super.characters(ch, start, length);
            stringBuffer.append(ch,start,length);
        }



    }

}
