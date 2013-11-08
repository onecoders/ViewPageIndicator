package net.jsiq.marketing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.http.CustomerHttpClient;
import net.jsiq.marketing.model.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	public static List<MenuItem> JSON2MenuItems(String json) {
		List<MenuItem> menuList = null;
		try {
			menuList = new ArrayList<MenuItem>();
			JSONArray jsonArr = new JSONArray(json);
			for (int i = 0; i < jsonArr.length(); i++) {
				MenuItem item = new MenuItem();
				JSONObject jsonMenu = jsonArr.getJSONObject(i);
				item.setMenuId(jsonMenu.getInt("menuId"));
				item.setMenuName(jsonMenu.getString("menuName"));
				item.setMenuIco(jsonMenu.getString("menuIco"));
				menuList.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	public static String getJSONFromUrl(String url) {
		InputStream is = null;
		try {
			HttpClient httpClient = CustomerHttpClient.getInstance();
			HttpPost httpPost = new HttpPost(url);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			return sb.toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(is);
		}
		return null;
	}

	private static void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
