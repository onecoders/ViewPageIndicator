package net.jsiq.marketing.util;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.model.CatalogItem;
import net.jsiq.marketing.model.Content;
import net.jsiq.marketing.model.MenuItem;

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

	public static List<Content> JSON2Content(String json) {
		List<Content> contentList = null;
		try {
			contentList = new ArrayList<Content>();
			JSONArray jsonArr = new JSONArray(json);
			for (int i = 0; i < jsonArr.length(); i++) {
				Content item = new Content();
				JSONObject jsonContent = jsonArr.getJSONObject(i);
				item.setContentId(jsonContent.getInt("contentId"));
				item.setContentTitle(jsonContent.getString("contentTitle"));
				item.setContentListPic(jsonContent.getString("contentListPic"));
				item.setContentTopPic(jsonContent.getString("contentTopPic"));
				item.setContentSummary(jsonContent.getString("contentSummary"));
				item.setTopShowFlag(jsonContent.getInt("topShowFlag"));
				contentList.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return contentList;
	}
}
