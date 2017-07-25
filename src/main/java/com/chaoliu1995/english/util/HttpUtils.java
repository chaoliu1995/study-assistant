package com.chaoliu1995.english.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpUtils {
	
	/**
	 * 请求url，返回字符串
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String sendRequest(String url) throws IOException{
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				//将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
				BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
				String line = null;
				while((line = br.readLine()) != null){
					//System.out.println(line);
					result = result + line;
				}
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.close();
		}
		return result;
	}
	
}
