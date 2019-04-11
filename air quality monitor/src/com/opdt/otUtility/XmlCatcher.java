package com.opdt.otUtility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;


public class XmlCatcher extends DefaultHandler{
	private static final String UVIURL = "http://opendata.epa.gov.tw/ws/Data/UV/?format=xml";
	String TAG = "XMLHandler";
	 Boolean currTag = false;
	 String currTagVal = "";  
	 public ParseData post = null;
	 public ArrayList<ParseData> UVIposts = new ArrayList<ParseData>();
	 public void get(){
			try{
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser mSaxParser = factory.newSAXParser();
				XMLReader mXmlReader = mSaxParser.getXMLReader();
				mXmlReader.setContentHandler(this);
				InputStream mInputStream = new URL(UVIURL).openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream,"UTF-8"));
				mXmlReader.parse(new InputSource(reader));
			//	mXmlReader.parse(new InputSource(mInputStream));
			}catch(Exception e){
				Log.e(TAG, e.getMessage()+"Exception");
			}
			
	 	}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			if(currTag) {
	            currTagVal = currTagVal + new String(ch, start, length);
	           
	            currTag = false;
	        }
		}
		//end elment
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			currTag = false;
	        if(localName.equalsIgnoreCase("SiteName"))
	        	//地區名稱
	            post.setSiteName(currTagVal);
	        else if(localName.equalsIgnoreCase("UVI"))
	        	//紫外線指數(字串)
	        	post.setUVI(currTagVal);
	        else if(localName.equalsIgnoreCase("UVI"))
	        	//紫外線指數(數字)
	        	post.setUVIvalue(Integer.parseInt(currTagVal));
	        else if(localName.equalsIgnoreCase("PublishTime"))
	        	//PublishTime 發佈日期
	        	post.setPubTime(currTagVal);
	        else if(localName.equalsIgnoreCase("County"))
	        	// 直轄市
	        	post.setcounty(currTagVal);
	        else if(localName.equalsIgnoreCase("PublishAgency"))
	        	//發布單位
	        	post.setPublishAgency(currTagVal);
	        else if(localName.equalsIgnoreCase("Data"))//post
	            UVIposts.add(post);
		}
		//start elent	
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			Log.i(TAG, "TAG: " + localName);
			currTag = true;
	        currTagVal = "";
	        if(localName.equals("Data"))//post
	            post = new ParseData();
		}
}
