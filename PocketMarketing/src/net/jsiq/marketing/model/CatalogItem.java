package net.jsiq.marketing.model;

public class CatalogItem {

	private int catalogId;
	private int menuId;
	private String catalogName;
	private int contentNum;
	private int firstContentId;

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public int getContentNum() {
		return contentNum;
	}

	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}

	public int getFirstContentId() {
		return firstContentId;
	}

	public void setFirstContentId(int firstContentId) {
		this.firstContentId = firstContentId;
	}

}
