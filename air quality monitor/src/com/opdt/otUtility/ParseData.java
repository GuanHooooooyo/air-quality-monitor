package com.opdt.otUtility;

public class ParseData implements java.io.Serializable{
		String SiteName,UVI,PubTime,county,PublishAgency;
		int UVIvalue;
		
		public String getPublishAgency() {
			return PublishAgency;
		}
		public void setPublishAgency(String publishAgency) {
			PublishAgency = publishAgency;
		}
		public String getcounty(){
			return county;
		}
		public void setcounty(String County){
			county = County;
		}
		public String getSiteName() {
			return SiteName;
		}

		public void setSiteName(String siteName) {
			SiteName = siteName;
		}

		public String getUVI() {
			return UVI;
		}

		public void setUVI(String uVI) {
			UVI = uVI;
		}

		public String getPubTime() {
			return PubTime;
		}

		public void setPubTime(String pubTime) {
			PubTime = pubTime;
		}
		public int getUVIvalue(){
			return UVIvalue;
		}
		public void setUVIvalue(int value){
			UVIvalue = value;
		}
}
