package net.jsiq.marketing.util;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.model.CatalogItem;
import net.jsiq.marketing.model.ContentItem;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	public static List<MenuItem> JSON2MenuItems(String json)
			throws JSONException {
		List<MenuItem> menuList = null;
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
		return menuList;
	}

	public static List<CatalogItem> JSON2CatalogItems(String json)
			throws JSONException {
		List<CatalogItem> catalogList = null;
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
		return catalogList;
	}

	public static List<ContentItem> JSON2ContentItems(String json)
			throws JSONException {
		List<ContentItem> contentList = null;
		contentList = new ArrayList<ContentItem>();
		JSONArray jsonArr = new JSONArray(json);
		for (int i = 0; i < jsonArr.length(); i++) {
			ContentItem item = new ContentItem();
			JSONObject jsonContent = jsonArr.getJSONObject(i);
			item.setContentId(jsonContent.getInt("contentId"));
			item.setContentTitle(jsonContent.getString("contentTitle"));
			item.setContentListPic(jsonContent.getString("contentListPic"));
			item.setContentTopPic(jsonContent.getString("contentTopPic"));
			item.setContentSummary(jsonContent.getString("contentSummary"));
			item.setTopShowFlag(jsonContent.getInt("topShowFlag"));
			contentList.add(item);
		}
		return contentList;
	}

	public static Weather JSON2Weather(String json) {
		Weather weather = null;
		try {
			weather = new Weather();
			JSONObject jObj = new JSONObject(json);
			JSONObject weatherJobj = jObj.getJSONObject("weatherinfo");
			weather.setCity(weatherJobj.getString("city"));
			weather.setDate_y(weatherJobj.getString("date_y"));
			weather.setWeek(weatherJobj.getString("week"));
			weather.setFchh(weatherJobj.getString("fchh"));
			weather.setTemp1(weatherJobj.getString("temp1"));
			weather.setWeather1(weatherJobj.getString("weather1"));
			weather.setImg1(weatherJobj.getString("img1"));
			weather.setWind1(weatherJobj.getString("wind1"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weather;
	}
}
