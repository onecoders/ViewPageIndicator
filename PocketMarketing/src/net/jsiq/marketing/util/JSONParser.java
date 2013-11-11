package net.jsiq.marketing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.http.CustomerHttpClient;
import net.jsiq.marketing.model.CatalogItem;
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
				item.setMenuIcon(jsonMenu.getString("menuIco"));
				menuList.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	public static List<CatalogItem> JSON2CatalogItems(String json) {
		List<CatalogItem> catalogList = null;
		try {
			catalogList = new ArrayList<CatalogItem>();
			JSONArray jsonArr = new JSONArray(json);
			for (int i = 0; i < jsonArr.length(); i++) {
				CatalogItem item = new CatalogItem();
				JSONObject jsonCatalog = jsonArr.getJSONObject(i);
				item.setCatalogId(jsonCatalog.getInt("catalogId"));
				item.setMenuId(jsonCatalog.getInt("menuId"));
				item.setCatalogName(jsonCatalog.getString("catalogName"));
				item.setContentNum(jsonCatalog.getInt("contentNum"));
				item.setFirstContentId(jsonCatalog.getInt("firstContentId"));
				catalogList.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return catalogList;
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
