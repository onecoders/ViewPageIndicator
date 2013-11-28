package net.jsiq.marketing.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {

	public static Bitmap getImageFromUrl(String url) {
		Bitmap bitmap = null;
		HttpGet httpGet = new HttpGet(url);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int reponseCode = httpResponse.getStatusLine().getStatusCode();
			if (reponseCode == HttpStatus.SC_OK) {
				InputStream inputStream = httpResponse.getEntity().getContent();
				bitmap = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static String getWeatherImg(String imageNo) {
		return "http://m.weather.com.cn/img/b" + imageNo + ".gif";
	}

}
