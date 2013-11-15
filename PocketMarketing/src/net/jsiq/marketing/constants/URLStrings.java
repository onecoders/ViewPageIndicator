package net.jsiq.marketing.constants;

public class URLStrings {

	private static final String DOMAIN_NAME = "http://www.jsiq.net:6060/";

	private static final String BASIC_URL = DOMAIN_NAME + "mpms/clientService/";

	public static final String GET_MENUS = BASIC_URL + "getMenus";

	public static final String GET_CATALOGS_BY_MENUID = BASIC_URL
			+ "getCatalogs/";

	public static final String GET_CONTENTS_BY_CATALOGID_PAGENO = BASIC_URL
			+ "getContents/";

	public static final String GET_CONTENT_BY_CONTENT_ID = BASIC_URL
			+ "viewContent/";

	public static final String EMPTY_TOP_SHOW_IMAGE = "http://wwww.jsiq.net:6060/resource";

}
