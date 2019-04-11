package com.opdt.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLHandler extends DefaultHandler {
 //http://ibus.tbkc.gov.tw/xmlbus/GetBusData.xml
 private static final String OPENDATA ="http://opendata.epa.gov.tw/ws/Data/AQX/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
 String TAG = "XMLHandler";
 Boolean currTag = false;
 String currTagVal = "";  
 public ParseValue post = null;
 public ArrayList<ParseValue> posts = new ArrayList<ParseValue>();

 	public void get(){
 		
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser mSaxParser = factory.newSAXParser();
			XMLReader mXmlReader = mSaxParser.getXMLReader();
			mXmlReader.setContentHandler(this);
			InputStream is = new URL(OPENDATA).openStream();
			
			/*建構BufferedReader讀取資料以UTF-8格式輸出
			 * BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			 * String Line = reader.readLine();
			 */	
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			mXmlReader.parse(new InputSource(isr));
			//mXmlReader.parse(new InputSource(Line));
		}catch(UnsupportedEncodingException e){
			Log.e(TAG, e.getMessage()+"Exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 	}
 	//建構BufferedReader 抓資料,以UTF-8格式輸出
//	public static BufferedReader StreamtoReader(InputStream is){
//		BufferedReader buff = null;
//		try {
//			InputStreamReader reader = new InputStreamReader(is,"UTF-8");
//			buff = new BufferedReader(reader);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return buff;
//	}
	//
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
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		currTag = false;
        //post_title,guid,ID,nameZh
//		if(localName.equalsIgnoreCase("County")){
//			localName = "高雄市";
//		}
        if(localName.equalsIgnoreCase("SiteName"))
        	//地區名稱
            post.setSiteName(currTagVal);
        else if(localName.equalsIgnoreCase("County"))
        	//縣市-->五都
        	 post.setCounty(currTagVal);
        else if (localName.equalsIgnoreCase("PM2.5"))
        	//PM2.5 字串資料
        	post.setPM25(currTagVal);
        else if(localName.equalsIgnoreCase("PM2.5"))
        	//PM2.5 轉換數字資料 Integer.parseInt
        	post.setPM25value(Integer.parseInt(currTagVal));
        else if(localName.equalsIgnoreCase("PublishTime"))
        	//PublishTime 發佈日期
        	post.setPublishTime(currTagVal);
        else if(localName.equalsIgnoreCase("Data"))//post
            posts.add(post);
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		Log.i(TAG, "TAG: " + localName);
		currTag = true;
        currTagVal = "";
     
        if(localName.equals("Data"))//post
            post = new ParseValue();
	}
}

