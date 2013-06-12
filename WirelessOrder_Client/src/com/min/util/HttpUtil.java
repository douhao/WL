package com.min.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	//����Base url����
	public static final String BASE_URL = "http://192.168.1.101:8888/WirelessOrder_Server/";
	
	//ͨ��url���HttpGet����
	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);		
		return request;
	}
	
	//ͨ��url���HttpPost����
	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);		
		return request;
	}
	
	//ͨ��HttpGet���HttpResponse����
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException, IOException {
		//ʵ����HttpResponse
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	//ͨ��HttpGet���HttpResponse����
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException {
		//ʵ����HttpResponse
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	//ͨ��url����post���󣬷���������
	public static String queryStringForPost(String url) {
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			//��ȡHttpResponseʵ��
			HttpResponse response = HttpUtil.getHttpResponse(request);
			//�ж������Ƿ�ɹ�
			if (response.getStatusLine().getStatusCode() == 200) {
				//��÷��ؽ��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		}
		return null;
	}
	
	//ͨ��url����get���󣬷���������
	public static String queryStringForGet(String url) {
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			//��ȡHttpResponseʵ��
			HttpResponse response = HttpUtil.getHttpResponse(request);
			//�ж������Ƿ�ɹ�
			if (response.getStatusLine().getStatusCode() == 200) {
				//��÷��ؽ��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		}
		return null;
	}
	
	//ͨ��HttpPost����post���󣬷���������
	public static String queryStringForPost(HttpPost request) {
		String result = null;
		try {
			//��ȡHttpResponseʵ��
			HttpResponse response = HttpUtil.getHttpResponse(request);
			//�ж������Ƿ�ɹ�
			if (response.getStatusLine().getStatusCode() == 200) {
				//��÷��ؽ��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		}
		return null;
	}
	
	//ͨ��HttpGet����get���󣬷���������
	public static String queryStringForGet(HttpGet request) {
		String result = null;
		try {
			//��ȡHttpResponseʵ��
			HttpResponse response = HttpUtil.getHttpResponse(request);
			//�ж������Ƿ�ɹ�
			if (response.getStatusLine().getStatusCode() == 200) {
				//��÷��ؽ��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�����쳣";
			return result;
		}
		return null;
	}
	
}