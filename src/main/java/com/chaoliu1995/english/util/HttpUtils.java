package com.chaoliu1995.english.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpUtils {
	
	/**
	 * 发送GET请求
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String sendGetRequest(String url,String charset) throws IOException{
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				//将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
				BufferedReader br = new BufferedReader(new InputStreamReader(is,charset));
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
	
	/**
	 * 发送POST请求
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String sendPostRequest(String url,Map<String,String> params,String charset) throws IOException{
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (params != null && !params.isEmpty()) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                nameValuePairs.add(new BasicNameValuePair(key, value));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream is = entity.getContent();
				//将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
				BufferedReader br = new BufferedReader(new InputStreamReader(is,charset));
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
