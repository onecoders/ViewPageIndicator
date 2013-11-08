package net.jsiq.marketing.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;

public class JsonHttpUtils {

	/**
	 * 向api发送get请求，返回从后台取得的信息。
	 * 
	 * @param url
	 * @return String
	 */
	public static String getRequest(String url) throws Exception {
		return getRequest(url, new DefaultHttpClient(new BasicHttpParams()));
	}

	/**
	 * 向api发送get请求，返回从后台取得的信息。
	 * 
	 * @param url
	 * @param client
	 * @return String
	 */
	public static String getRequest(String url, DefaultHttpClient client)
			throws Exception {
		String result = null;
		int statusCode = 0;
		HttpGet getMethod = new HttpGet(url);
		try {
			// getMethod.setHeader("User-Agent", USER_AGENT);

			HttpResponse httpResponse = client.execute(getMethod);
			// statusCode == 200 正常
			statusCode = httpResponse.getStatusLine().getStatusCode();
			// 处理返回的httpResponse信息
			result = retrieveInputStream(httpResponse.getEntity());
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			getMethod.abort();
		}
		return result;
	}

	/**
	 * 处理httpResponse信息,返回String
	 * 
	 * @param httpEntity
	 * @return String
	 */
	public static String retrieveInputStream(HttpEntity httpEntity) {

		int length = (int) httpEntity.getContentLength();
		// the number of bytes of the content, or a negative number if unknown.
		// If the content length is known but exceeds Long.MAX_VALUE, a negative
		// number is returned.
		// length==-1，下面这句报错，println needs a message
		if (length < 0)
			length = 10000;
		StringBuffer stringBuffer = new StringBuffer(length);
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					httpEntity.getContent(), HTTP.UTF_8);
			char buffer[] = new char[length];
			int count;
			while ((count = inputStreamReader.read(buffer, 0, length - 1)) > 0) {
				stringBuffer.append(buffer, 0, count);
			}
		} catch (UnsupportedEncodingException e) {
		} catch (IllegalStateException e) {
		} catch (IOException e) {
		}
		return stringBuffer.toString();
	}
}
