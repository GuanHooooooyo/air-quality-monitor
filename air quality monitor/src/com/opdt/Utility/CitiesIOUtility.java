package com.opdt.Utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CitiesIOUtility {
	public static BufferedReader StreamtoReader(InputStream is){
		
		BufferedReader buff = null;
		try {
			InputStreamReader reader = new InputStreamReader(is,"UTF-8");
			buff = new BufferedReader(reader);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buff;
	}
}
