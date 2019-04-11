package com.opdt.Utility;

import java.net.URLEncoder;

public class ParseValue implements java.io.Serializable{
/**
 * setter and getter 
 */
	String SiteName,County,PM10,PM25,PublishTime;
	int PM25Value;
	int icon;
public String getSiteName() {
	return SiteName;
}

public void setSiteName(String SiteName) {
	this.SiteName = SiteName;
}

public String getCounty() {
	return County;
}

public void setCounty(String County) {
	this.County = County;
}

public String getPM10() {
	return PM10;
}

public void setPM10(String pM10) {
	PM10 = pM10;
}

public String getPM25() {
	return PM25;
}
public int getPM25Value() {
	return PM25Value;
}
public void setPM25value(int value){
	PM25Value = value;
}
public void setPM25(String pM25) {
	PM25 = pM25;
	
}
public String getPublishTime(){
	return PublishTime;
}
public void setPublishTime(String time){
	PublishTime = time;
}
public int getIcon() {
	return icon;
}

public void setIcon(int icon) {
	this.icon = icon;
}



}
