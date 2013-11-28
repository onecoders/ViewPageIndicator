package net.jsiq.marketing.constants;

public class URLStrings {

	private static final String DOMAIN_NAME = "http://58.241.60.12/";

	private static final String BASIC_URL = DOMAIN_NAME + "mpms/clientService/";

	public static final String GET_MENUS = BASIC_URL + "getMenus";

	public static final String GET_CATALOGS_BY_MENUID = BASIC_URL
			+ "getCatalogs/";

	public static final String GET_CONTENTS_BY_CATALOGID_PAGENO = BASIC_URL
			+ "getContents/";

	public static final String GET_CONTENT_BY_CONTENT_ID = BASIC_URL
			+ "viewContent/";

	public static final String GET_BAR_CODE = BASIC_URL
			+ "getTwoDimensionCodeImage/";

	public static final String GET_SEARCH_BY_SEARCH_KEY = BASIC_URL
			+ "searchContents/";

	public static final String GET_START_IMAGE = BASIC_URL + "getStartImage";

	public static final String GET_WEATHER_INFO = "http://m.weather.com.cn/data/101191101.html";

}
