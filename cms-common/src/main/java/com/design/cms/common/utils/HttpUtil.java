package com.design.cms.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import com.design.cms.common.assist.DesignException;

public class HttpUtil {
	public static final String DEF_CHATSET = "UTF-8";

    public static final int DEF_CONN_TIMEOUT = 10000;

    public static final int DEF_READ_TIMEOUT = 10000;
    
    
    
	public static void post(String urlStr,Map<String,String> params){
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> i : params.entrySet()) {
			builder.append(i.getKey()).append("=").append(i.getValue()).append("&");
		}
		post(urlStr,builder.toString());
	}



	public static String post(String urlStr, String param) {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(DEF_CONN_TIMEOUT);
			
			OutputStream os = con.getOutputStream();
			os.write(param.getBytes(DEF_CHATSET));
			os.flush();
			os.close();
			
			int resultCode= con.getResponseCode();
			if(HttpURLConnection.HTTP_OK==resultCode){
				StringBuffer sb=new StringBuffer();
			    String readLine=null;
			    BufferedReader responseReader=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			    while((readLine=responseReader.readLine())!=null){
			        sb.append(readLine).append("\n");
			    }
			    responseReader.close();
			    return sb.toString();
			}else{
				throw new DesignException(con.getResponseMessage());
			}
			
		} catch (Exception e) {
			throw new DesignException(e.getMessage());
		}
	}
}
